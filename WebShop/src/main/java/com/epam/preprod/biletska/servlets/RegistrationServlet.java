package com.epam.preprod.biletska.servlets;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.dto.RegistrationFormDto;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.ICaptchaService;
import com.epam.preprod.biletska.services.IImageService;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.services.exception.UserCreateException;
import com.epam.preprod.biletska.validation.IValidationService;
import com.epam.preprod.biletska.validation.impl.ValidationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.raiseExceptionIfNoParam;

/**
 * Registration servlet.
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegistrationServlet extends HttpServlet {

    private static final String ERROR_CAPTCHA = "errorCaptcha";
    private static final String ERROR_EMAIL = "errorEmail";
    private static final String ERROR_IMAGE = "errorImage";

    /**
     * The CaptchaServlet service.
     */
    private ICaptchaService captchaService;
    /**
     * The User service.
     */
    private IUserService userService;

    /**
     * The CaptchaServlet life span.
     */
    private int captchaLifeSpan;
    /**
     * Image related service
     */
    private IImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        captchaService = raiseExceptionIfNoParam(config, Constants.CAPTCHA_SERVICE, ICaptchaService.class);
        userService = raiseExceptionIfNoParam(config, Constants.USER_SERVICE, IUserService.class);
        captchaLifeSpan = raiseExceptionIfNoParam(config, Constants.CAPTCHA_LIFE_SPAN, Integer.class);
        imageService = raiseExceptionIfNoParam(config, Constants.IMAGE_SERVICE, IImageService.class);
    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute(Constants.CAPTCHA_KEY, new Date().getTime() + captchaLifeSpan);

        httpServletRequest.setAttribute(Constants.USER_FORM, httpServletRequest.getSession().getAttribute(Constants.USER_FORM));
        httpServletRequest.getSession().removeAttribute(Constants.USER_FORM);

        httpServletRequest.setAttribute(Constants.REGISTRATION_ERRORS, httpServletRequest.getSession().getAttribute(Constants.REGISTRATION_ERRORS));
        httpServletRequest.getSession().removeAttribute(Constants.REGISTRATION_ERRORS);

        String nextJSP = Constants.REGISTER_JSP;
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(nextJSP);
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        RegistrationFormDto registrationFormDto = RegistrationServlet.extract(httpServletRequest);
        IValidationService validationService = (ValidationService) httpServletRequest.getServletContext().getAttribute(Constants.VALIDATION_SERVICE);
        Map<String, String> errors = new HashMap<>();

        if (!captchaService.removeExpiredCaptcha(() -> httpServletRequest)) {
            errors.put(ERROR_CAPTCHA, "Invalid captcha");
        }

        validationService.fillRegistrationErrorMap(registrationFormDto, userService, errors);

        if (!imageService.uploadImage(httpServletRequest, registrationFormDto.getEmail())) {
            errors.put(ERROR_IMAGE, "Wrong extension");
        }

        if (errors.isEmpty()) {
            try {
                userService.addUser(RegistrationServlet.convert(registrationFormDto));
            } catch (UserCreateException uce) {
                uce.printStackTrace();
            }
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + Constants.INDEX_HTML);
        } else {
            session.setAttribute(Constants.USER_FORM, registrationFormDto);
            session.setAttribute(Constants.REGISTRATION_ERRORS, errors);
            httpServletResponse.sendRedirect(Constants.REGISTER_SERVLET);
        }
    }

    private static RegistrationFormDto extract(HttpServletRequest servletRequest) {
        RegistrationFormDto registrationFormDto = new RegistrationFormDto(
                servletRequest.getParameter("user_name"),
                servletRequest.getParameter("user_surname"),
                servletRequest.getParameter("user_password"),
                servletRequest.getParameter("confirm_password"),
                servletRequest.getParameter("user_email"),
                Boolean.parseBoolean(servletRequest.getParameter("notification"))
        );
        return registrationFormDto;
    }

    private static User convert(RegistrationFormDto registrationFormDto) {
        User user = new User();
        user.setFirstName(registrationFormDto.getFirstName());
        user.setLastName(registrationFormDto.getLastName());
        user.setEmail(registrationFormDto.getEmail());
        user.setPassword(registrationFormDto.getPassword());
        user.setMailingEnabled(registrationFormDto.isMailingEnable());
        return user;
    }
}

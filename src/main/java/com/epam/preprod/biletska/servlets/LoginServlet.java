package com.epam.preprod.biletska.servlets;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.dto.LoginDto;
import com.epam.preprod.biletska.dto.LoginExtractor;
import com.epam.preprod.biletska.entity.User;
import com.epam.preprod.biletska.services.AbstractIEntityService;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.services.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.preprod.biletska.servlets.CommonDefinitions.raiseExceptionIfNoParam;

/**
 * Login servlet.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    final static Logger logger = LoggerFactory.getLogger(AbstractIEntityService.class);

    private static final String ERROR_NO_LOGIN = "noLoginExists";

    /**
     * The User service.
     */
    private IUserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        userService = raiseExceptionIfNoParam(config, Constants.USER_SERVICE, IUserService.class);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        LoginDto loginData = new LoginExtractor().extract(request);

        try {
            User loggedUser = userService.getUserByLogin(loginData.getLogin());
            request.getSession(true).setAttribute(CommonDefinitions.LOGGED_USER, loggedUser);
            request.getSession().setAttribute("user", userService.getUserByLogin(loginData.getLogin()));
            if(!loginData.getPassword().equals(loggedUser.getPassword())){
                errors.put(ERROR_NO_LOGIN, "Invalid password");
                request.getSession(true).setAttribute(CommonDefinitions.LOGGED_USER, null);
            }
        } catch (UserNotFoundException unfe) {
            logger.error("Error occurred when getting user by login: {}, {}", loginData.getLogin(), unfe.getMessage());
            errors.put(ERROR_NO_LOGIN, "No login found");
        }

        if (!errors.isEmpty()) {
            request.setAttribute(Constants.INDEX_ERRORS, errors);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute(CommonDefinitions.LOGGED_USER) == null) {
            doLogin(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + Constants.INDEX_JSP);
    }
}

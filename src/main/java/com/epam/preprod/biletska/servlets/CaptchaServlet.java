package com.epam.preprod.biletska.servlets;

import com.epam.preprod.biletska.dto.CaptchaDto;
import com.epam.preprod.biletska.services.ICaptchaService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.preprod.biletska.Constants.CAPTCHA_PARAMETER;
import static com.epam.preprod.biletska.Constants.CAPTCHA_SERVICE;

/**
 * The type CaptchaServlet.
 */
public class CaptchaServlet extends HttpServlet {

    private ICaptchaService captchaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        captchaService = (ICaptchaService) config.getServletContext().getAttribute(CAPTCHA_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        CaptchaDto captcha = captchaService.createCaptcha();
        captchaService.setCaptcha(captcha, () -> httpServletRequest);
        captchaService.draw(captcha, httpServletResponse.getOutputStream());
        httpServletRequest.getSession().setAttribute(CAPTCHA_PARAMETER, captcha);
    }
}

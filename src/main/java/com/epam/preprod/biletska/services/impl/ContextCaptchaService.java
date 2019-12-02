package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.captcha.CaptchaContainer;
import com.epam.preprod.biletska.dto.CaptchaDto;
import com.epam.preprod.biletska.services.IRequestAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static com.epam.preprod.biletska.Constants.CAPTCHA_KEY;

/**
 * The type Context captcha service.
 */
public class ContextCaptchaService extends AbstractCaptchaService {

    /**
     * Instantiates a new Context captcha service.
     *
     * @param captchaContainer the captcha container
     * @param expireAfter      the captcha expiry time
     */
    public ContextCaptchaService(CaptchaContainer captchaContainer, int expireAfter) {
        super(captchaContainer, expireAfter);
    }

    @Override
    public void setCaptcha(CaptchaDto captcha, IRequestAware requestAware) {
        super.setCaptcha(captcha, requestAware);
        ServletContext servletContext = requestAware.getRequest().getServletContext();
        servletContext.setAttribute(CAPTCHA_KEY, String.valueOf(captcha.getKey()));
    }

    @Override
    public boolean removeExpiredCaptcha(IRequestAware requestAware) {
        HttpServletRequest request = requestAware.getRequest();
        ServletContext servletContext = request.getServletContext();
        long captchaKey = Long.parseLong((String) servletContext.getAttribute(CAPTCHA_KEY));
        boolean checkResult = checkCaptcha(captchaKey, request.getParameter(Constants.CAPTCHA_PARAMETER));
        if (checkResult) {
            servletContext.removeAttribute(CAPTCHA_KEY);
        }
        return checkResult;
    }
}

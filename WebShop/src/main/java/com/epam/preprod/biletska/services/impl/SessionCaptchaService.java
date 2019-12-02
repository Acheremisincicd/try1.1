package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.Constants;
import com.epam.preprod.biletska.captcha.CaptchaContainer;
import com.epam.preprod.biletska.dto.CaptchaDto;
import com.epam.preprod.biletska.services.IRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.epam.preprod.biletska.Constants.CAPTCHA_KEY;

/**
 * The type Session captcha service.
 */
public class SessionCaptchaService extends AbstractCaptchaService {

    /**
     * Instantiates a new Session captcha service.
     *
     * @param captchaContainer  the captcha container
     * @param captchaExpireTime the captcha expire time
     */
    public SessionCaptchaService(CaptchaContainer captchaContainer, int captchaExpireTime) {
        super(captchaContainer, captchaExpireTime);
    }

    @Override
    public void setCaptcha(CaptchaDto captcha, IRequestAware requestAware) {
        super.setCaptcha(captcha, requestAware);
        HttpSession session = requestAware.getRequest().getSession();
        session.setAttribute(CAPTCHA_KEY, String.valueOf(captcha.getKey()));
    }

    @Override
    public boolean removeExpiredCaptcha(IRequestAware requestAware) {
        HttpServletRequest request = requestAware.getRequest();
        HttpSession session = request.getSession();
        long captchaKey = Long.parseLong((String) session.getAttribute(CAPTCHA_KEY));
        boolean checkResult = checkCaptcha(captchaKey, request.getParameter(Constants.CAPTCHA_PARAMETER));
        if (checkResult) {
            session.removeAttribute(CAPTCHA_KEY);
        }
        return checkResult;
    }
}
package com.epam.preprod.biletska.captcha;

import com.epam.preprod.biletska.services.ICaptchaService;
import com.epam.preprod.biletska.services.impl.ContextCaptchaService;
import com.epam.preprod.biletska.services.impl.SessionCaptchaService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type CaptchaServlet factory.
 */
public class CaptchaFactory {

    private Map<String, ICaptchaService> serviceMap = new HashMap<>();

    /**
     * Instantiates a new CaptchaServlet factory.
     *
     * @param captchaContainer   the captcha container
     * @param captchaExpireAfter the captcha expiry time
     */
    public CaptchaFactory(CaptchaContainer captchaContainer, int captchaExpireAfter) {
        serviceMap.put("session", new SessionCaptchaService(captchaContainer, captchaExpireAfter));
        serviceMap.put("context", new ContextCaptchaService(captchaContainer, captchaExpireAfter));
    }

    /**
     * Gets captcha service method
     *
     * @param serviceName : the service name, for null arguments return service with session use
     * @return the service
     */
    public ICaptchaService getService(String serviceName) {
        return Optional.of(serviceMap.get(serviceName)).orElse(serviceMap.get("session"));
    }
}


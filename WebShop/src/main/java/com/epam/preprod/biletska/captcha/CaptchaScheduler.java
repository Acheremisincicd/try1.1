package com.epam.preprod.biletska.captcha;

/**
 * The type CaptchaServlet scheduler.
 */
public class CaptchaScheduler implements Runnable {

    private CaptchaContainer captchaContainer;

    /**
     * Instantiates a new CaptchaServlet scheduler.
     *
     * @param captchaContainer the captcha container
     */
    public CaptchaScheduler(CaptchaContainer captchaContainer) {
        this.captchaContainer = captchaContainer;
    }

    @Override
    public void run() {
        captchaContainer.removeExpiredCaptcha();
    }
}


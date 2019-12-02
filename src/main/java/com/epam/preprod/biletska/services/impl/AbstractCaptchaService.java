package com.epam.preprod.biletska.services.impl;

import com.epam.preprod.biletska.captcha.CaptchaContainer;
import com.epam.preprod.biletska.dto.CaptchaDto;
import com.epam.preprod.biletska.services.ICaptchaService;
import com.epam.preprod.biletska.services.IRequestAware;
import com.github.cage.Cage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * The type Abstract captcha service
 */
public abstract class AbstractCaptchaService implements ICaptchaService {

    /**
     * The captcha's container
     */
    private CaptchaContainer captchaContainer;

    /**
     * The captcha's generator
     */
    private Cage captchaGenerator;

    /**
     * The captcha's lifespan
     */
    private int expireAfter;

    /**
     * Instantiates a new Abstract captcha service.
     *
     * @param captchaContainer the captcha container
     */
    public AbstractCaptchaService(CaptchaContainer captchaContainer, int expireAfter) {
        this.expireAfter = expireAfter;
        this.captchaContainer = captchaContainer;
        captchaGenerator = new Cage();
    }

    /**
     * Create captcha dto captcha dto.
     *
     * @return the captcha dto
     */
    @Override
    public CaptchaDto createCaptcha() {
        Random random = new Random();
        String value = ("" + random.nextInt(10)
                + random.nextInt(10)
                + random.nextInt(10)
                + random.nextInt(10));
        return new CaptchaDto(value, getExpireAfter());
    }

    @Override
    public void draw(CaptchaDto captcha, OutputStream outStream) throws IOException {
        captchaGenerator.draw(captcha.getValue(), outStream);
    }

    /**
     * Gets captcha dto.
     *
     * @param key the key
     * @return the captcha dto
     */
    @Override
    public CaptchaDto getCaptcha(long key) {
        return captchaContainer.getCaptcha(key);
    }

    /**
     * Store captcha.
     */
    @Override
    public void setCaptcha(CaptchaDto captcha, IRequestAware request) {
        captchaContainer.addCaptcha(captcha);
    }

    /**
     * Returns period of time in milliseconds after which captcha w'll be expired
     *
     * @return the captcha dto
     */
    public int getExpireAfter() {
        return expireAfter;
    }

    /**
     * Check captcha boolean.
     *
     * @return the boolean
     */
    protected boolean checkCaptcha(long captchaKey, String captchaValue) {
        CaptchaDto captcha = getCaptcha(captchaKey);
        if (captcha == null || System.currentTimeMillis() > captcha.getExpireAfterDate().getTime()) {
            return false;
        }
        return captcha.getValue().equals(captchaValue);
    }
}

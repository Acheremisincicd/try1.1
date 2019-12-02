package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.dto.CaptchaDto;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The interface CaptchaServlet service.
 */
public interface ICaptchaService {

    /**
     * Create captcha dto.
     *
     * @return the captcha dto
     */
    CaptchaDto createCaptcha();

    /**
     * Add captcha.
     *
     * @param captcha the captcha dto
     */
    void setCaptcha(CaptchaDto captcha, IRequestAware request);

    /**
     * Returns captcha.
     */
    CaptchaDto getCaptcha(long key);

    /**
     * Validate captcha boolean and if it's invalidate - remove it
     *
     * @return the boolean
     */
    boolean removeExpiredCaptcha(IRequestAware requestAware);

    /**
     * Draw captcha for users
     *
     * @param captcha the captcha dto
     * @throws IOException the io exception
     */
    void draw(CaptchaDto captcha, OutputStream outStream) throws IOException;
}

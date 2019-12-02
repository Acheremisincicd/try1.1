package com.epam.preprod.biletska.captcha;

import com.epam.preprod.biletska.dto.CaptchaDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Captcha container
 */
public class CaptchaContainer {

    private List<CaptchaDto> captchaList;

    /**
     * Instantiates a new CaptchaServlet container.
     */
    public CaptchaContainer() {
        captchaList = new ArrayList<>();
    }

    /**
     * Add captcha.
     *
     * @param captcha the captcha
     */
    public void addCaptcha(CaptchaDto captcha) {
        captchaList.add(captcha);
    }

    /**
     * Gets captcha.
     *
     * @param key the key
     * @return the captcha
     */
    public CaptchaDto getCaptcha(long key) {
        return captchaList.stream()
                .filter(captchaDto -> captchaDto.getKey() == key)
                .findFirst().get();
    }

    /**
     * Remove inactive captcha.
     */
    public void removeExpiredCaptcha() {
        Date curDate = new Date();
        List<CaptchaDto> expireCaptchaList = captchaList.stream()
                .filter(captchaDto -> curDate.after(captchaDto.getExpireAfterDate()))
                .collect(Collectors.toList());
        captchaList.removeAll(expireCaptchaList);
    }
}

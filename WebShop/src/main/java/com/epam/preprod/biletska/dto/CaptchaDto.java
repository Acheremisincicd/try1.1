package com.epam.preprod.biletska.dto;

import java.util.Date;

/**
 * CaptchaServlet dto.
 */
public class CaptchaDto {

    /**
     * The captcha's creating time
     */
    private Date creationDate;

    /**
     * The captcha's value
     */
    private String value;

    /**
     * The captcha's lifespan
     */
    private long expireAfter;

    /**
     * Instantiates a new CaptchaServlet dto.
     *
     * @param value       the value
     * @param expireAfter the expireAfter
     */
    public CaptchaDto(String value, long expireAfter) {
        this.creationDate = new Date();
        this.value = value;
        this.expireAfter = expireAfter;
    }

    public long getExpireAfter() {
        return expireAfter;
    }

    public String getValue() {
        return value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getKey() {
        return getCreationDate().getTime();
    }

    public Date getExpireAfterDate() {
        return new Date(getCreationDate().getTime() + getExpireAfter());
    }
}

package com.epam.preprod.biletska.locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;

public interface LocaleStrategy {

    /**
     * Get Locale.
     *
     * @param request  request
     * @param response response
     * @return locale
     */
    Locale getLocale(ServletRequest request, ServletResponse response);

    /**
     * Set Locale.
     *
     * @param request  request
     * @param response response
     */
    void saveLocale(ServletRequest request, ServletResponse response, Locale locale);
}


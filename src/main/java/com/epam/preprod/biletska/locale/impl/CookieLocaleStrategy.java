package com.epam.preprod.biletska.locale.impl;

import com.epam.preprod.biletska.locale.LocaleStrategy;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;

/**
 * Strategy implementation for getting and saving locale to cookies
 */
public class CookieLocaleStrategy implements LocaleStrategy {

    @Override
    public Locale getLocale(ServletRequest request, ServletResponse response) {
        Cookie[] cockies = ((HttpServletRequest) request).getCookies();

        if (ArrayUtils.isEmpty(cockies)) {
            return null;
        }

        return Arrays.stream(cockies)
                .filter(c -> c.getName() != null && c.getName().equalsIgnoreCase("locale"))
                .map(c -> new Locale(c.getValue()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveLocale(ServletRequest request, ServletResponse response, Locale locale) {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        ServletContext context = request.getServletContext();
        Cookie cookie = new Cookie("locale", locale.getLanguage());
        cookie.setMaxAge((Integer) context.getAttribute("localeCookieMaxAge"));
        servletResponse.addCookie(cookie);
    }
}


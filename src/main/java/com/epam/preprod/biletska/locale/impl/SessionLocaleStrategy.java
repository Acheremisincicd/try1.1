package com.epam.preprod.biletska.locale.impl;

import com.epam.preprod.biletska.locale.LocaleStrategy;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Strategy implementation for getting and saving locale to session
 */
public class SessionLocaleStrategy implements LocaleStrategy {

    @Override
    public Locale getLocale(ServletRequest request, ServletResponse response) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        return (Locale) session.getAttribute("locale");
    }

    @Override
    public void saveLocale(ServletRequest request, ServletResponse response, Locale locale) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        session.setAttribute("locale", locale);
    }
}


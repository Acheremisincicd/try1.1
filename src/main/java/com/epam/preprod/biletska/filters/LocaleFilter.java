package com.epam.preprod.biletska.filters;

import com.epam.preprod.biletska.locale.LocaleHttpServletRequestWrapper;
import com.epam.preprod.biletska.locale.LocaleStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class LocaleFilter implements Filter {

    private List<String> supportedLocales;
    private String defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        this.supportedLocales = (List<String>) context.getAttribute("locales");
        this.defaultLocale = (String) context.getAttribute("defaultLocale");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        Locale locale;
        ServletContext context = request.getServletContext();
        LocaleStrategy localeStrategy = (LocaleStrategy) context.getAttribute("localeStrategy");
        if (checkLangIsPresent(request)) {
            locale = getLocaleFromRequestParam(request);
            localeStrategy.saveLocale(request, response, locale);
        } else {
            locale = localeStrategy.getLocale(request, response);
            if (locale == null) {
                locale = getLocaleFromRequest(request);
            }
        }
        ServletRequest requestWrapper = new LocaleHttpServletRequestWrapper((HttpServletRequest) request, locale);
        filterChain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
    }

    /**
     * Checks if language is set up
     *
     * @param request ServletRequest
     * @return returns true if language is set up
     */
    private boolean checkLangIsPresent(ServletRequest request) {
        return StringUtils.isNotEmpty(request.getParameter("lang"));
    }

    /**
     * Gets locale from request parameters
     *
     * @param request ServletRequest
     * @return returns supported locale that matches obtained or returns default
     */
    private Locale getLocaleFromRequestParam(ServletRequest request) {
        String lang = request.getParameter("lang");
        return supportedLocales.contains(lang) ? new Locale(lang) : new Locale(defaultLocale);
    }

    /**
     * Gets all possible locales
     *
     * @param request ServletRequest
     * @return returns supported locale that matches obtained or returns default
     */
    private Locale getLocaleFromRequest(ServletRequest request) {
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (supportedLocales.contains(locale.getLanguage())) {
                return locale;
            }
        }
        return new Locale(defaultLocale);
    }
}

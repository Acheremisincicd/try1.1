package com.epam.preprod.biletska.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Cache-Control", "no-cache, no-store");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setDateHeader("Expires", 0);
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
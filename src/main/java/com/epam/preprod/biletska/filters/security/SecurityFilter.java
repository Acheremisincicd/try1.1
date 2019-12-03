package com.epam.preprod.biletska.filters.security;

import com.epam.preprod.biletska.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Security filter
 */
public class SecurityFilter implements Filter {

    private SecurityHandler handler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        handler = new SecurityHandler((Map<String, List<String>>) filterConfig.getServletContext().getAttribute("constraints"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        String role = Objects.isNull(user) ? "unknown" : user.getRole();
        String url = request.getRequestURI();

        if (handler.isPathAcceptable(role, url)) {
            filterChain.doFilter(request, response);
        } else {
            request.getSession().setAttribute("errorMsg", "U have no rights for that");
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}

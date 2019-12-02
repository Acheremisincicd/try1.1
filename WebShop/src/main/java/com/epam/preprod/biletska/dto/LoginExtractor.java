package com.epam.preprod.biletska.dto;

import javax.servlet.http.HttpServletRequest;

/**
 * Login dto Extractor class.
 */
public class LoginExtractor {

    public LoginDto extract(HttpServletRequest servletRequest) {
        LoginDto loginBean = new LoginDto(
                servletRequest.getParameter("login_enter"),
                servletRequest.getParameter("password_enter")
        );
        return loginBean;
    }
}

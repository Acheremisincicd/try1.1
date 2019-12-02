package com.epam.preprod.biletska.services;

import javax.servlet.http.HttpServletRequest;

/**
 * Functional interface to pass request
 */
public interface IRequestAware {

    /**
     * functional interface for getting request
     *
     * @return request
     */
    HttpServletRequest getRequest();
}

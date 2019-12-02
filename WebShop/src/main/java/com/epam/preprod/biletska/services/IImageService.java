package com.epam.preprod.biletska.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The interface Image service.
 */
public interface IImageService {

    /**
     * upload image method
     *
     * @param httpServletRequest request
     * @param login              login
     * @return : true if images is uploaded and false if it is not
     * @throws IOException      : IOException
     * @throws ServletException : ServletException
     */
    boolean uploadImage(HttpServletRequest httpServletRequest, String login) throws IOException, ServletException;
}

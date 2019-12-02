package com.epam.preprod.biletska.servlets;

import javax.servlet.ServletConfig;

/**
 * CommonDefinitions class.
 */
public final class CommonDefinitions {

    public static final String ERROR_IMAGE = "errorImage";

    private CommonDefinitions() {
    }

    public static final String LOGGED_USER = "loggedUser";

    public static <T> T raiseExceptionIfNoParam(ServletConfig config, String configName, Class<T> expectedType) {
        Object configObj = config.getServletContext().getAttribute(configName);
        if (configObj != null || (expectedType.isAssignableFrom(configObj.getClass()))) {
            return (T) configObj;
        }
        throw new IllegalStateException(String.format("No %s configured", configName));
    }
}

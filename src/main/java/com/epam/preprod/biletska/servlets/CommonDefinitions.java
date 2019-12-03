package com.epam.preprod.biletska.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CommonDefinitions class.
 */
public final class CommonDefinitions {

    public static final String ROUTE_ROOT = "/";
    public static final String ROUTE_PRODUCTS = "./product";
    public static final String LOGGED_USER = "loggedUser";
    public static final String CURRENT_CART = "shoppingCart";
    public static final String ERROR_IMAGE = "errorImage";

    public static class ErrorCollector {

        private static ErrorCollector instance;
        private Map<String, String> errors;

        private ErrorCollector() {
            errors = new HashMap<>();
        }

        public static synchronized ErrorCollector getInstance() {
            if (instance == null) {
                instance = new ErrorCollector();
            }
            return instance;
        }

        public ErrorCollector collect(String errKey, String errMsg) {
            instance.errors.put(errKey, errMsg);
            return instance;
        }

        public Optional<Map<String, String>> getErrors() {
            return Optional.of(instance.errors).filter(m -> !m.isEmpty());
        }
    }

    private CommonDefinitions() {
    }

    public static <T> T raiseExceptionIfNoParam(ServletConfig config, String configName, Class<T> expectedType) {
        Object configObj = config.getServletContext().getAttribute(configName);
        if (configObj == null || !(expectedType.isAssignableFrom(configObj.getClass()))) {
            throw new IllegalStateException(String.format("No %s configured", configName));
        } else {
            return (T) configObj;
        }
    }

    public static <T> void setRequestAttr(HttpServletRequest request, String attrName, T attrValue) {
        Optional.ofNullable(attrValue).ifPresent(p -> request.setAttribute(attrName, attrValue));
    }

    public static void removeRequestAttr(HttpServletRequest request, String attrName) {
        Optional.ofNullable(request).ifPresent(s -> s.removeAttribute(attrName));
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getSessionAttr(HttpServletRequest request, Class<T> attrClass, String attrName) {
        Optional<HttpSession> session = Optional.ofNullable(request.getSession());
        return (Optional<T>) session.map(s -> s.getAttribute(attrName)).flatMap(p -> {
            if (attrClass.isAssignableFrom(p.getClass())) {
                return Optional.of(p);
            }
            return Optional.empty();
        });
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getRequestAttr(HttpServletRequest request, Class<T> attrClass, String attrName) {
        return (Optional<T>) Optional.ofNullable(request.getAttribute(attrName)).flatMap(p -> {
            if (attrClass.isAssignableFrom(p.getClass())) {
                return Optional.of(p);
            }
            return Optional.empty();
        });
    }

    public static Optional<String> getSessionAttrString(HttpServletRequest request, String attrName) {
        return getSessionAttr(request, String.class, attrName);
    }

    public static <T> void setSessionAttr(HttpServletRequest request, String paramName, T paramValue) {
        Optional.ofNullable(request.getSession()).ifPresent(s -> s.setAttribute(paramName, paramValue));
    }

    public static void removeSessionAttr(HttpServletRequest request, String attrName) {
        Optional.ofNullable(request.getSession()).ifPresent(s -> s.removeAttribute(attrName));
    }

    public static Optional<Integer> getRequestParamInt(HttpServletRequest request, String paramName) {
        return toIntOptional(getRequestParamString(request, paramName));
    }

    public static Optional<Boolean> getRequestPramBool(HttpServletRequest request, String attrName) {
        return toBoolOptional(getRequestParamString(request, attrName));
    }

    public static Optional<Integer> getRequestAttrInt(HttpServletRequest request, String attrName) {
        return getRequestAttr(request, Integer.class, attrName);
    }

    public static Optional<String> getRequestAttrString(HttpServletRequest request, String attrName) {
        return getRequestAttr(request, String.class, attrName);
    }

    public static Optional<Integer> getSessionAttrInt(HttpServletRequest request, String attrName) {
        return getSessionAttr(request, Integer.class, attrName);
    }

    private static Optional<Integer> toIntOptional(Optional<String> strOpt) {
        if (strOpt.isPresent()) {
            try {
                return Optional.of(Integer.parseInt(strOpt.get()));
            } catch (NumberFormatException nfe) {
            }
        }
        return Optional.empty();
    }

    public static Optional<String> getRequestParamString(HttpServletRequest request, String paramName) {
        return Optional.ofNullable(request.getParameter(paramName));
    }

    private static Optional<Boolean> toBoolOptional(Optional<String> strOpt) {
        if (strOpt.isPresent()) {
            try {
                return Optional.of(Boolean.valueOf(strOpt.get()));
            } catch (NumberFormatException nfe) {
            }
        }
        return Optional.empty();
    }
}

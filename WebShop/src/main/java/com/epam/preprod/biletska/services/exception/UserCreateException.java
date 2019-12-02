package com.epam.preprod.biletska.services.exception;

/**
 * UserCreateException class.
 */
public class UserCreateException extends IllegalStateException {

    public UserCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCreateException(String s) {
        super(s);
    }
}

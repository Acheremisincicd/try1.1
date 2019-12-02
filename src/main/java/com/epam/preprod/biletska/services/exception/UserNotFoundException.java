package com.epam.preprod.biletska.services.exception;

/**
 * UserNotFoundException class.
 */
public class UserNotFoundException extends IllegalStateException {

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}

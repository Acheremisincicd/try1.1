package com.epam.preprod.biletska;

/**
 * Repository constants class.
 */
public final class RepositoryConstants {

    /**
     * Utility class doesn't allowed to instantiate it
     */
    private RepositoryConstants() {
    }

    /**
     * The constant ID.
     */
    public final static String ID = "id";

    /**
     * The constant USER_FIRST_NAME.
     */
    public final static String USER_FIRST_NAME = "first_name";

    /**
     * The constant USER_LAST_NAME.
     */
    public final static String USER_LAST_NAME = "last_name";

    /**
     * The constant USER_LOGIN.
     */
    public final static String USER_LOGIN = "login";

    /**
     * The constant USER_EMAIL.
     */
    public final static String USER_EMAIL = "email";

    /**
     * The constant USER_PASSWORD.
     */
    public final static String USER_PASSWORD = "password";

    /**
     * The constant USER_MAILING.
     */
    public final static String USER_MAILING = "subscribe";

    /**
     * The constant QUERY_USER_INSERT.
     */
    public final static String QUERY_USER_INSERT = "INSERT INTO user (first_name, last_name, email, password, subscribe) VALUES(?,?,?,?,?)";

    public final static String QUERY_USER_GET = "SELECT * FROM user WHERE email=?";
    public final static String QUERY_USER_ALL = "SELECT * FROM user";
    public final static String QUERY_USER_UPDATE = "UPDATE USER SET first_name=?, last_name=?, email=?, password=?, subscribe=? WHERE id=?";
    public final static String QUERY_USER_DELETE = "DELETE FROM USER WHERE email=?";

    /**
     * The constant USER_SELECT_LOGIN.
     */
    public final static String USER_SELECT_LOGIN = "SELECT * FROM user WHERE email = ?";
}

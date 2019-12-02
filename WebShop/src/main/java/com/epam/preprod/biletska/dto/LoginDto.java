package com.epam.preprod.biletska.dto;

/**
 * Login Dto class.
 */
public class LoginDto {

    private String login;
    private String password;

    public LoginDto(String login_enter, String password_enter) {
        login = login_enter;
        password = password_enter;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

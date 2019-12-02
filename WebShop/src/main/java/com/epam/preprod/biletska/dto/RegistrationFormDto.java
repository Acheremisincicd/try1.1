package com.epam.preprod.biletska.dto;

import java.util.Objects;

/**
 * The type RegistrationServlet form dto.
 */
public class RegistrationFormDto {

    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private boolean isMailingEnable;

    /**
     * Instantiates a new RegistrationServlet form dto.
     *
     * @param firstName       the first name
     * @param lastName        the last name
     * @param password        the password
     * @param confirmPassword the confirmPassword
     * @param email           the email
     * @param isMailingEnable the isMailingEnable
     */
    public RegistrationFormDto(String firstName, String lastName, String password, String confirmPassword, String email, boolean isMailingEnable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.isMailingEnable = isMailingEnable;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMailingEnable() {
        return isMailingEnable;
    }

    public void setMailingEnable(boolean mailingEnable) {
        this.isMailingEnable = mailingEnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationFormDto that = (RegistrationFormDto) o;
        return isMailingEnable() == that.isMailingEnable()
                && Objects.equals(getFirstName(), that.getFirstName())
                && Objects.equals(getLastName(), that.getLastName())
                && Objects.equals(getPassword(), that.getPassword())
                && Objects.equals(getConfirmPassword(), that.getConfirmPassword())
                && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPassword(), getConfirmPassword(), getEmail(), isMailingEnable());
    }
}

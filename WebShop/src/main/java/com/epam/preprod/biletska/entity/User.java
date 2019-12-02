package com.epam.preprod.biletska.entity;

import java.util.Objects;

/**
 * User class.
 */
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isMailingEnabled;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, boolean isMailingEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isMailingEnabled = isMailingEnabled;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMailingEnabled() {
        return isMailingEnabled;
    }

    public void setMailingEnabled(boolean mailingEnabled) {
        this.isMailingEnabled = mailingEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isMailingEnabled == user.isMailingEnabled &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, isMailingEnabled);
    }

    public String getKey() {
        return email;
    }
}

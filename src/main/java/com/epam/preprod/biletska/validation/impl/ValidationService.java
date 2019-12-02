package com.epam.preprod.biletska.validation.impl;

import com.epam.preprod.biletska.dto.RegistrationFormDto;
import com.epam.preprod.biletska.services.IUserService;
import com.epam.preprod.biletska.validation.IValidationService;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ValidationServlet service implementation.
 */
public class ValidationService implements IValidationService {

    private static final String LOGIN_VALIDATION_REGEXP = "^[a-zA-Z0-9._-]{3,}$";
    private static final String EMAIL_VALIDATION_REGEXP = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String ERROR_PASSWORD = "errorPassword";
    private static final String ERROR_CONFIRM_PASSWORD = "errorConfirmPassword";
    private static final String ERROR_EMAIL = "errorEmail";
    private static final String ERROR_LAST_NAME = "errorLastName";
    private static final String ERROR_FIRST_NAME = "errorFirstName";

    @Override
    public void fillRegistrationErrorMap(RegistrationFormDto registrationFormDto, IUserService userService, Map<String, String> errorMap) {
        validateFirstName(registrationFormDto, errorMap);
        validateLastName(registrationFormDto, errorMap);
        validateLogin(registrationFormDto, errorMap, userService);
        validateEmail(registrationFormDto, errorMap);
        validatePassword(registrationFormDto, errorMap);
    }

    private void validateLogin(RegistrationFormDto registrationFormDto, Map<String, String> errorMap, IUserService userService) {
        String login = registrationFormDto.getFirstName();
        Pattern p = java.util.regex.Pattern.compile(LOGIN_VALIDATION_REGEXP);
        Matcher m = p.matcher(login);
        if (!m.matches()) {
            errorMap.put(ERROR_LOGIN, "Please provide valid login!");
        } else if (userService.getUserByLogin(login) != null) {
            errorMap.put(ERROR_LOGIN, "User with such login already exist!");
        }
    }

    private void validatePassword(RegistrationFormDto registrationFormDto, Map<String, String> errorMap) {
        String password = registrationFormDto.getPassword();
        if (Objects.isNull(password)) {
            errorMap.put(ERROR_PASSWORD, "Password should not be empty!");
        } else {
            String confirm_password = registrationFormDto.getConfirmPassword();
            if (!Objects.equals(confirm_password, password)) {
                errorMap.put(ERROR_CONFIRM_PASSWORD, "Passwords do not match");
            }
        }
    }

    private void validateEmail(RegistrationFormDto registrationFormDto, Map<String, String> errorMap) {
        if (registrationFormDto.getEmail() == null) {
            errorMap.put(ERROR_EMAIL, "Email should not be empty!");
        } else {
            Pattern p = java.util.regex.Pattern.compile(EMAIL_VALIDATION_REGEXP);
            Matcher m = p.matcher(registrationFormDto.getEmail());
            if (!m.matches()) {
                errorMap.put(ERROR_EMAIL, "Please provide correct email!");
            }
        }
    }

    private void validateLastName(RegistrationFormDto registrationFormDto, Map<String, String> errorMap) {
        if (registrationFormDto.getLastName() == null) {
            errorMap.put(ERROR_LAST_NAME, "Last name name could not be empty!");
        } else if (registrationFormDto.getLastName() != null && registrationFormDto.getLastName().length() < 3) {
            errorMap.put(ERROR_LAST_NAME, "Too short last name, min 3 character");
        }
    }

    private void validateFirstName(RegistrationFormDto registrationFormDto, Map<String, String> errorMap) {
        if (registrationFormDto.getFirstName() == null) {
            errorMap.put(ERROR_FIRST_NAME, "First name could not be empty!");
        } else if (registrationFormDto.getFirstName() != null && registrationFormDto.getFirstName().length() < 3) {
            errorMap.put(ERROR_FIRST_NAME, "Too short firs name, min 3 character");
        }
    }
}

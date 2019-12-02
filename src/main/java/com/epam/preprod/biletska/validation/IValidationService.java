package com.epam.preprod.biletska.validation;

import com.epam.preprod.biletska.dto.RegistrationFormDto;
import com.epam.preprod.biletska.services.IUserService;

import java.util.Map;

/**
 * The interface ValidationServlet service.
 */
public interface IValidationService {

    /**
     * Check form is valid map.
     *
     * @param registrationFormDto the registration form dto
     * @param userService         the user service
     * @param errorMap            the error map
     */
    void fillRegistrationErrorMap(RegistrationFormDto registrationFormDto, IUserService userService, Map<String, String> errorMap);
}

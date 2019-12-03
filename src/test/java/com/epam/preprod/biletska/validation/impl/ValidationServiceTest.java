package com.epam.preprod.biletska.validation.impl;

import com.epam.preprod.biletska.dto.RegistrationFormDto;
import com.epam.preprod.biletska.services.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test class for ValidationService
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    /**
     * Registration form DTO
     */
    private RegistrationFormDto registrationFormDto;

    /**
     * Validation service
     */
    private ValidationService validationService;

    /**
     * Error map
     */
    private Map<String, String> errors;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        validationService = new ValidationService();
        errors = new HashMap<>();
        when(userService.getUserByLogin(anyString())).thenReturn(null);
    }

    /**
     * Test for checking service with valid data
     */
    @Test
    public void shouldTestValidRegistrationCase() {
        registrationFormDto = new RegistrationFormDto("Ivan", "Ivanov", "Aa1234567",
                "Aa1234567", "ivan@gmail.com", true,"admin");
        validationService.fillRegistrationErrorMap(registrationFormDto, userService, errors);
        assertTrue(errors.isEmpty());
    }

    /**
     * Test for checking service with invalid data
     */
    @Test
    public void shouldTestInvalidRegistrationCase() {
        registrationFormDto = new RegistrationFormDto("a", "b", "v22",
                "g33", ".@-->.ru", true, "user");
        validationService.fillRegistrationErrorMap(registrationFormDto, userService, errors);
        assertEquals(5, errors.size());
    }

    /**
     * Test for checking service with null data
     */
    @Test
    public void shouldTestNullRegistrationCase() {
        registrationFormDto = new RegistrationFormDto("aaa", null, null,
                null, null, true, "user");
        validationService.fillRegistrationErrorMap(registrationFormDto, userService, errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.containsValue("Email should not be empty!"));
        assertTrue(errors.containsValue("Password should not be empty!"));
        assertTrue(errors.containsValue("Last name name could not be empty!"));
    }
}

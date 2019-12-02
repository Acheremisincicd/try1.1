const EMAIL_REG_EXP = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;
const PASSWORD_REG_EXP = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
const NAME_REG_EXP = /^[\p{L}]{3,32}$/u;

function setValidationClasses(field, isValid) {
    field.removeClass('is-valid');
    field.removeClass('is-invalid');
    if (isValid) {
        field.addClass('is-valid');
        return;
    }
    field.addClass('is-invalid');
}

const checkers = {
    validateByRegExp: (regExp, value) => regExp.test(value)
};

function validateField(field, regex) {
    setValidationClasses(field, checkers.validateByRegExp(regex, field.val()));
}

function validateConfirmFields(confirmField, field) {
    setValidationClasses(confirmField, confirmField.val() !== "" && confirmField.val() === field.val());
}

function validateRegistrationForm(registrationForm) {
    const userNameInput = registrationForm.find("#user-name");
    const userSurnameInput = registrationForm.find("#user-surname");
    const userPasswordInput = registrationForm.find("#user-password");
    const userPasswordConfirmInput = registrationForm.find("#user-password-confirm");
    const userEmailInput = registrationForm.find("#user-email");

    validateField(userNameInput, NAME_REG_EXP);
    validateField(userSurnameInput, NAME_REG_EXP);
    validateField(userPasswordInput, PASSWORD_REG_EXP);
    validateField(userEmailInput, EMAIL_REG_EXP);
    validateConfirmFields(userPasswordConfirmInput, userPasswordInput);

    return registrationForm.find(':input.is-invalid').length > 0;
}

function handleRegistrationFormSubmit(event) {
    if (validateRegistrationForm($(event.target))) {
        event.preventDefault();
    }
}

function handleInputFieldEvent(event, regex) {
    validateField($(event.target), regex)
}

function handleConfirmFiled(event, field) {
    validateConfirmFields($(event.target), field);
}

function initializeRegistrationFormValidation(registrationForm) {
    const userNameInput = $("#user-name");
    const userSurnameInput = $("#user-surname");
    const userPasswordInput = $("#user-password");
    const userPasswordConfirmInput = $("#user-password-confirm");
    const userEmailInput = $("#user-email");

    registrationForm.on('submit', handleRegistrationFormSubmit);
    userNameInput.on('blur', function (event) {
        handleInputFieldEvent(event, NAME_REG_EXP);
    });
    userSurnameInput.on('blur', function (event) {
        handleInputFieldEvent(event, NAME_REG_EXP);
    });
    userPasswordInput.on('blur', function (event) {
        handleInputFieldEvent(event, PASSWORD_REG_EXP);
    });
    userPasswordConfirmInput.on('blur', function (event) {
        handleConfirmFiled(event, userPasswordInput);
    });
    userEmailInput.on('input', function (event) {
        handleInputFieldEvent(event, EMAIL_REG_EXP);
    });
}

const registrationForm = $("#registration-form");
initializeRegistrationFormValidation(registrationForm);
const EMAIL_REG_EXP = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;
const PASSWORD_REG_EXP = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
const NAME_REG_EXP = /^[\p{L}]{3,32}$/u;

function addValidationClasses(field, isValid) {
    field.classList.toggle('is-valid', isValid);
    field.classList.toggle('is-invalid', !isValid);
}

const checkers = {
    validateByRegExp: (regExp, value) => regExp.test(value)
};

function validateField(inputField, regExp) {
    addValidationClasses(inputField, checkers.validateByRegExp(regExp, inputField.value));
}

function validateConfirmFields (confirmField, field) {
    if (confirmField.value != "") {
        addValidationClasses(confirmField, confirmField.value === field.value);
    } else {
        addValidationClasses(confirmField, false)
    }
}


function validateRegistrationForm(registrationForm) {
    const registrationFormInputs = registrationForm.querySelectorAll('input');
    const registrationFormInputsArray = Array.prototype.slice.call(registrationFormInputs);
    const userNameInput = registrationForm.elements.user_name;
    const userSurnameInput = registrationForm.elements.user_surname;
    const userPasswordInput = registrationForm.elements.user_password;
    const userPasswordConfirmInput = registrationForm.elements.confirm_password;
    const userEmailInput = registrationForm.elements.user_email;

    validateField(userNameInput, NAME_REG_EXP);
    validateField(userSurnameInput, NAME_REG_EXP);
    validateField(userPasswordInput, PASSWORD_REG_EXP);
    validateField(userEmailInput, EMAIL_REG_EXP);
    validateConfirmFields(userPasswordConfirmInput, userPasswordInput);

    return !registrationFormInputsArray.some(input => input.classList.contains('is-invalid'));
}

function handleInputFieldEvent(event, regExp) {
    validateField(event.target, regExp);
}

function handleConfirmFiled(event, field) {
    validateConfirmFields (event.target, field);
}

function handleRegistrationFormSubmit(event) {
    const isValid = validateRegistrationForm(event.target);

    if (!isValid) {
        event.preventDefault();
    }
}

function initializeRegistrationFormValidation(registrationForm) {
    registrationFormInputs = registrationForm.elements;
    const userNameInput = registrationFormInputs.user_name;
    const userSurnameInput = registrationFormInputs.user_surname;
    const userPasswordInput = registrationFormInputs.user_password;
    const userPasswordConfirmInput = registrationFormInputs.confirm_password;
    const userEmailInput = registrationFormInputs.user_email;

    registrationForm.addEventListener('submit', handleRegistrationFormSubmit);
    userNameInput.addEventListener('blur', function (event) {
        handleInputFieldEvent(event, NAME_REG_EXP);
    });
    userSurnameInput.addEventListener('blur', function (event) {
        handleInputFieldEvent(event, NAME_REG_EXP);
    });
    userPasswordInput.addEventListener('blur', function (event) {
        handleInputFieldEvent(event, PASSWORD_REG_EXP);
    });
    userPasswordConfirmInput.addEventListener('blur', function (event) {
        handleConfirmFiled(event, userPasswordInput);
    });
    userEmailInput.addEventListener('input', function (event) {
        handleInputFieldEvent(event, EMAIL_REG_EXP);
    });
}

const registrationForm = document.getElementById('registration-form');
initializeRegistrationFormValidation(registrationForm);
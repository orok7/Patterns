package com.eins.learn.patterns.chainofresp;

public class ValidationChainDemo {
    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm("asd@asd.asd", "login1", "+38(000)000-00-00",
                "asdqweasd", "asdqweasd");
        validateAllForm(registrationForm);

        registrationForm = new RegistrationForm("asd@asd.asd", "login1", "+38(000)0asd00-00-00",
                "asdqweasd", "asqweasd");
        validateAllForm(registrationForm);
    }

    private static void validateAllForm(RegistrationForm registrationForm) {
        RegistrationValidation emailValidation = new EmailValidation();
        RegistrationValidation loginValidation = new LoginValidation();
        RegistrationValidation phoneValidation = new PhoneValidation();
        RegistrationValidation passwordValidation = new PasswordValidation();
        emailValidation.setNext(loginValidation);
        loginValidation.setNext(phoneValidation);
        phoneValidation.setNext(passwordValidation);

        ValidationResult result = new ValidationResult();
        emailValidation.validate(registrationForm, result);
        System.out.println(result);
        System.out.println("Can" + (result.isValid() ? "" : "not") + " be registered");
    }
}

package com.eins.learn.patterns.chainofresp;

public class PhoneValidation extends RegistrationValidation {

    private static final int PHONE_MAX_LENGTH = 17;

    @Override
    protected void doValidation(RegistrationForm form, ValidationResult result) {
        result.setPhoneValid(someValidationLogic(form.getPhone()));
    }

    private boolean someValidationLogic(String phone) {
        return phone != null && !phone.isEmpty() && phone.length() <= PHONE_MAX_LENGTH;
    }
}

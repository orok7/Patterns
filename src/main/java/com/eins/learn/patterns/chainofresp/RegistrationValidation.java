package com.eins.learn.patterns.chainofresp;

public abstract class RegistrationValidation {
    RegistrationValidation next;

    void setNext(RegistrationValidation next) {
        this.next = next;
    }

    void validate(RegistrationForm form, ValidationResult result) {
        doValidation(form, result);
        if (next != null) {
            next.validate(form, result);
        }
    }

    abstract void doValidation(RegistrationForm form, ValidationResult result);
}

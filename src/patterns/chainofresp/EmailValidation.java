package patterns.chainofresp;

public class EmailValidation extends RegistrationValidation {

    @Override
    protected void doValidation(RegistrationForm form, ValidationResult result) {
        result.setEmailValid(someValidationLogic(form.getEmail()));
    }

    private boolean someValidationLogic(String email) {
        return email != null && !email.isEmpty() && email.contains("@");
    }
}

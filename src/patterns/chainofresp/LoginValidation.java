package patterns.chainofresp;

public class LoginValidation extends RegistrationValidation {

    @Override
    protected void doValidation(RegistrationForm form, ValidationResult result) {
        result.setLoginValid(someValidationLogic(form.getLogin()));
    }

    private boolean someValidationLogic(String login) {
        return login != null && !login.isEmpty();
    }
}

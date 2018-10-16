package patterns.chainofresp;

public class PasswordValidation extends RegistrationValidation {

    private static final int PASS_MIN_LENGTH = 8;

    @Override
    protected void doValidation(RegistrationForm form, ValidationResult result) {
        result.setPasswordValid(someValidationLogic(form.getPassword(), form.getPasswordConfirmation()));
    }

    private boolean someValidationLogic(String pass, String passConf) {
        return pass != null && !pass.isEmpty() && pass.length() > PASS_MIN_LENGTH && pass.equals(passConf);
    }
}

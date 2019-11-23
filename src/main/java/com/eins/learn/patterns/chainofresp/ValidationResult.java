package com.eins.learn.patterns.chainofresp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    boolean isEmailValid;
    boolean isLoginValid;
    boolean isPhoneValid;
    boolean isPasswordValid;

    public boolean isValid() {
        return isEmailValid && isLoginValid && isPhoneValid && isPasswordValid;
    }
}

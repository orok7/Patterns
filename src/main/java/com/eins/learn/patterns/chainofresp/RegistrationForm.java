package com.eins.learn.patterns.chainofresp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
    private String email;
    private String login;
    private String phone;
    private String password;
    private String passwordConfirmation;
}

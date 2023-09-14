package com.programmers.ticketparis.dto.member.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {

    private static final Pattern VALIDATE_PASSWORD = Pattern.compile(
        "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$");

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        return VALIDATE_PASSWORD.matcher(password).matches();
    }
}

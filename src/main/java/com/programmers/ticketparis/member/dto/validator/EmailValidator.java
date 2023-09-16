package com.programmers.ticketparis.member.dto.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

    private static final Pattern VALIDATE_EMAIL = Pattern.compile(
        "^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{1,255}\\.[a-zA-Z]{2,}$");

    @Override
    public void initialize(EmailValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }

        return VALIDATE_EMAIL.matcher(email).matches();
    }
}


package com.programmers.ticketparis.dto.member.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegistrationNumberValidator implements ConstraintValidator<RegistrationNumberValid, String> {

    private static final Pattern VALIDATE_REGISTRATION_NUMBER = Pattern.compile("^\\d{2}-\\d{2}-\\d{5}$");

    @Override
    public void initialize(RegistrationNumberValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String registrationNumber,
        ConstraintValidatorContext context) {
        if (registrationNumber == null) {
            return false;
        }

        if (!VALIDATE_REGISTRATION_NUMBER.matcher(registrationNumber).matches()) {
            return false;
        }

        return true;
    }
}

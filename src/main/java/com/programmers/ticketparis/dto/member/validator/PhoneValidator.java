package com.programmers.ticketparis.dto.member.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {

    private static final Pattern VALIDATE_PHONE = Pattern.compile("^\\d{3}-\\d{4}-\\d{4}$");

    @Override
    public void initialize(PhoneValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) {
            return false;
        }

        return VALIDATE_PHONE.matcher(phone).matches();
    }
}


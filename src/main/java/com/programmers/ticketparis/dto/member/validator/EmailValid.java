package com.programmers.ticketparis.dto.member.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValid {

    String message() default "이메일은 320자 이하(공백 미포함)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

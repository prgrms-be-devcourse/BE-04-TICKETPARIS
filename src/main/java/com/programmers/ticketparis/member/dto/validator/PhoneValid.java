package com.programmers.ticketparis.member.dto.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface PhoneValid {

    String message() default "핸드폰 번호는 다음 형식(000-0000-0000)으로 입력";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

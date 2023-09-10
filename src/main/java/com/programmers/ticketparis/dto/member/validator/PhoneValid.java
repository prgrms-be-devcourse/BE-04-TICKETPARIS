package com.programmers.ticketparis.dto.member.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValid {

    String message() default "핸드폰 번호는 다음 형식(000-0000-0000)에 맞추어 입력";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

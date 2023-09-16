package com.programmers.ticketparis.member.dto.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface UsernameValid {

    String message() default "아이디는 8자 이상 15자 이하(영어, 숫자, 공백불가)로 입력";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

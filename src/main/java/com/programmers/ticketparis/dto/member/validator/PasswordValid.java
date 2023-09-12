package com.programmers.ticketparis.dto.member.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {

    String message() default "비밀번호는 8자 이상 20자 이하 (<영어, 숫자, 특수문자> 포함, 공백불가)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

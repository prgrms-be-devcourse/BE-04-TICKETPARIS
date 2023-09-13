package com.programmers.ticketparis.dto.member.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RegistrationNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegistrationNumberValid {

    String message() default "사업자 등록 번호는 다음 형식(00-00-00000)에 맞추어 입력";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

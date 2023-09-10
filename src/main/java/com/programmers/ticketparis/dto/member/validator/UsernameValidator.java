package com.programmers.ticketparis.dto.member.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameValid, String> {

    private static final Pattern VALIDATE_USERNAME = Pattern.compile("^[a-zA-Z0-9]{8,15}$");

    //todo: 에러 응답 형식 협의 된 후에 아래 메세지도 포함해서 줄지 결정하기.
    private static final String ERROR_MESSAGE_VALIDATE_USERNAME_NULL = "username에 null이 들어올 수 없음";
    private static final String ERROR_MESSAGE_VALIDATE_USERNAME_FORMAT = "아이디는 8자 이상 15자 이하(영어, 숫자, 공백불가)";

    @Override
    public void initialize(UsernameValid constraintAnnotation) {
    }

    //todo: 에러 응답 형식 협의 된 후에 ConstraintValidatorContext 전달 로직 구현
    @Override
    public boolean isValid(String username,
        ConstraintValidatorContext context) {
        if (username == null) {
            return false;
        }

        if (!VALIDATE_USERNAME.matcher(username).matches()) {
            return false;
        }

        return true;
    }
}

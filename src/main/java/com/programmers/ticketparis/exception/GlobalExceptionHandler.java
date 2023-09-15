package com.programmers.ticketparis.exception;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.programmers.ticketparis.dto.ErrorData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorData handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("[ERROR] {} => 요청 URL : {}", NOT_FOUND.getMessage(), e.getRequestURL(), e);

        return ErrorData.builder()
            .exceptionRule(NOT_FOUND)
            .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorData handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        log.error(
            "[ERROR] {} => 지원 메서드 : {} | 요청 메서드 : {}",
            METHOD_NOT_ALLOWED.getMessage(),
            e.getSupportedMethods(),
            e.getMethod(),
            e
        );

        return ErrorData.builder()
            .exceptionRule(METHOD_NOT_ALLOWED)
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorData handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult()
            .getAllErrors()
            .stream()
            .map(FieldError.class::cast)
            .toList();

        log.error("[ERROR] {}", BAD_REQUEST.getMessage(), e);
        errors.forEach(
            error -> log.error("{} => {} : {}", error.getDefaultMessage(), error.getField(), error.getRejectedValue()));

        Object[] rejectedValues = errors.stream()
            .map(FieldError::getRejectedValue)
            .toArray();

        return ErrorData.builder()
            .exceptionRule(BAD_REQUEST)
            .rejectedValues(rejectedValues)
            .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorData handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause().getCause();

        // LocalDatetime.parse()가 불가능한 형식으로 datetime 값을 입력받는 경우
        if (Objects.nonNull(cause) && cause instanceof DateTimeParseException dateTimeParseException) {
            String parsedString = dateTimeParseException.getParsedString();
            log.error("[ERROR] {} => 원인 값 : {}", BAD_REQUEST.getMessage(), parsedString, e);

            return ErrorData.builder()
                .exceptionRule(BAD_REQUEST)
                .rejectedValues(new Object[] {parsedString})
                .build();
        }

        log.error("[ERROR] {}", BAD_REQUEST.getMessage(), e);

        return ErrorData.builder()
            .exceptionRule(BAD_REQUEST)
            .build();
    }

    @ExceptionHandler(BusinessException.class)
    public ErrorData handleBusinessException(BusinessException e) {
        ExceptionRule exceptionRule = e.getExceptionRule();
        Object[] rejectedValues = e.getRejectedValues();

        log.error("[ERROR] {} => 원인 값 : {}", exceptionRule.getMessage(), rejectedValues, e);

        return ErrorData.builder()
            .exceptionRule(exceptionRule)
            .rejectedValues(rejectedValues)
            .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorData handleException(Exception e) {
        log.error("[ERROR] {}", INTERNAL_SERVER_ERROR.getMessage(), e);

        return ErrorData.builder()
            .exceptionRule(INTERNAL_SERVER_ERROR)
            .build();
    }
}

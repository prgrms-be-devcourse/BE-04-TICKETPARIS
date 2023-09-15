package com.programmers.ticketparis.exception;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
        log.error("{} : 요청 URL - {}", NOT_FOUND.getMessage(), e.getRequestURL(), e);

        return makeErrorData(NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorData handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        log.error(
            "{} : 지원 메서드 - {}, 실제 요청 메서드 - {}",
            METHOD_NOT_ALLOWED.getMessage(),
            e.getSupportedMethods(),
            e.getMethod(),
            e
        );

        return makeErrorData(METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorData handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        List<String> rejectedValues = allErrors.stream()
            .map(FieldError.class::cast)
            .map(error -> {
                Object rejectedValue = error.getRejectedValue();
                return Objects.isNull(rejectedValue) ? "null" : rejectedValue.toString();
            })
            .toList();

        log.error("{} : 원인 값 - {}", BAD_REQUEST.getMessage(), rejectedValues, e);

        return makeErrorData(BAD_REQUEST, rejectedValues);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorData handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause().getCause();

        // LocalDatetime.parse()가 불가능한 형식으로 datetime 값을 입력받는 경우
        if (Objects.nonNull(cause) && cause instanceof DateTimeParseException dateTimeParseException) {
            List<String> rejectedValues = List.of(dateTimeParseException.getParsedString());
            log.error("{} : 원인 값 - {}", BAD_REQUEST.getMessage(), rejectedValues, e);

            return makeErrorData(BAD_REQUEST, rejectedValues);
        }

        log.error(BAD_REQUEST.getMessage(), e);

        return makeErrorData(BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ErrorData handleBusinessException(BusinessException e) {
        ExceptionRule exceptionRule = e.getRule();
        List<String> rejectedValues = e.getRejectedValues();

        log.error("{} : 원인 값 - {}", exceptionRule.getMessage(), rejectedValues, e);

        return makeErrorData(exceptionRule, rejectedValues);
    }

    @ExceptionHandler(Exception.class)
    public ErrorData handleException(Exception e) {
        log.error(INTERNAL_SERVER_ERROR.getMessage(), e);

        return makeErrorData(INTERNAL_SERVER_ERROR);
    }

    private ErrorData makeErrorData(ExceptionRule exceptionRule) {
        return ErrorData.builder()
            .exceptionRule(exceptionRule)
            .build();
    }

    private ErrorData makeErrorData(ExceptionRule exceptionRule, List<String> rejectedValues) {
        return ErrorData.builder()
            .exceptionRule(exceptionRule)
            .rejectedValues(rejectedValues)
            .build();
    }
}

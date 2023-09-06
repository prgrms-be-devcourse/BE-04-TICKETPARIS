package com.programmers.ticketparis.exception;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.programmers.ticketparis.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("{} : 요청 URL - {}", NOT_FOUND.getMessage(), e.getRequestURL(), e);
        ErrorResponse response = ErrorResponse.of(NOT_FOUND);

        return ResponseEntity.status(NOT_FOUND.getStatus()).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("{} : 지원 메서드 - {}, 실제 요청 메서드 - {}", METHOD_NOT_ALLOWED.getMessage(), e.getSupportedMethods(), e.getMethod(), e);
        ErrorResponse response = ErrorResponse.of(METHOD_NOT_ALLOWED);

        return ResponseEntity.status(METHOD_NOT_ALLOWED.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        List<String> rejectedValues = allErrors.stream()
            .map(FieldError.class::cast)
            .map(error -> {
                Object rejectedValue = error.getRejectedValue();
                return Objects.isNull(rejectedValue) ? "null" : rejectedValue.toString();
            })
            .toList();

        log.error("{} : 원인 값 - {}", BAD_REQUEST.getMessage(), rejectedValues, e);
        ErrorResponse response = ErrorResponse.of(BAD_REQUEST, rejectedValues);

        return ResponseEntity.status(BAD_REQUEST.getStatus()).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(BAD_REQUEST.getMessage(), e);

        Throwable rootCause = e.getRootCause();
        ErrorResponse response = ErrorResponse.of(BAD_REQUEST);

        // LocalDatetime.parse()가 불가능한 형식으로 datetime 값을 입력받는 경우
        if (rootCause instanceof DateTimeParseException dateTimeParseException) {
            List<String> rejectedValues = List.of(dateTimeParseException.getParsedString());
            response = ErrorResponse.of(BAD_REQUEST, rejectedValues);
        }

        return ResponseEntity.status(BAD_REQUEST.getStatus()).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ExceptionRule rule = e.getRule();
        List<String> rejectedValues = e.getRejectedValues();

        log.error("{} : 원인 값 - {}", rule.getMessage(), rejectedValues, e);
        ErrorResponse response = ErrorResponse.of(rule, rejectedValues);

        return ResponseEntity.status(rule.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(INTERNAL_SERVER_ERROR.getMessage(), e);
        ErrorResponse response = ErrorResponse.of(INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR.getStatus()).body(response);
    }
}

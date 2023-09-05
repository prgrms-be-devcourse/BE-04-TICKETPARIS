package com.programmers.ticketparis.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.programmers.ticketparis.exception.ExceptionRule;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String code;
    private final String message;
    private List<String> data;

    protected ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    protected ErrorResponse(String code, String message, List<String> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ErrorResponse of(ExceptionRule rule) {
        return new ErrorResponse(rule.name(), rule.getMessage());
    }

    public static ErrorResponse of(ExceptionRule rule, List<String> rejectedValues) {
        return new ErrorResponse(rule.name(), rule.getMessage(), rejectedValues);
    }
}

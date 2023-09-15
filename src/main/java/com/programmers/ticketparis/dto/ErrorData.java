package com.programmers.ticketparis.dto;

import com.programmers.ticketparis.exception.ExceptionRule;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorData {

    private ExceptionRule exceptionRule;
    private Object[] rejectedValues;

    @Builder
    private ErrorData(ExceptionRule exceptionRule, Object[] rejectedValues) {
        this.exceptionRule = exceptionRule;
        this.rejectedValues = rejectedValues;
    }
}

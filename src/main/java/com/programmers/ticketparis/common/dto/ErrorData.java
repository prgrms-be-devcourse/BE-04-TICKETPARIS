package com.programmers.ticketparis.common.dto;

import com.programmers.ticketparis.common.exception.ExceptionRule;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorData {

    private final ExceptionRule exceptionRule;
    private final Object[] rejectedValues;

    @Builder
    private ErrorData(ExceptionRule exceptionRule, Object[] rejectedValues) {
        this.exceptionRule = exceptionRule;
        this.rejectedValues = rejectedValues;
    }
}

package com.programmers.ticketparis.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ExceptionRule exceptionRule;
    private final Object[] rejectedValues;

    protected BusinessException(ExceptionRule exceptionRule, Object... rejectedValues) {
        super(exceptionRule.getMessage());
        this.exceptionRule = exceptionRule;
        this.rejectedValues = rejectedValues;
    }
}

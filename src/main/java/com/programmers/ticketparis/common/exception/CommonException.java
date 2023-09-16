package com.programmers.ticketparis.common.exception;

public class CommonException extends BusinessException {

    public CommonException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

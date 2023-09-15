package com.programmers.ticketparis.exception;

public class CommonException extends BusinessException {

    public CommonException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

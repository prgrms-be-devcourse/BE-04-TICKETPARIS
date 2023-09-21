package com.programmers.ticketparis.member.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class CustomerException extends BusinessException {

    public CustomerException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }

    public CustomerException(ExceptionRule rule, Throwable e, Object... rejectedValues) {
        super(rule, e, rejectedValues);
    }
}

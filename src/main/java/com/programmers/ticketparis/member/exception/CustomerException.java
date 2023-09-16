package com.programmers.ticketparis.member.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class CustomerException extends BusinessException {

    public CustomerException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

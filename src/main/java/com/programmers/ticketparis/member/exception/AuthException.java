package com.programmers.ticketparis.member.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class AuthException extends BusinessException {

    public AuthException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

package com.programmers.ticketparis.member.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class SellerException extends BusinessException {

    public SellerException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }

    public SellerException(ExceptionRule rule, Throwable e, Object... rejectedValues) {
        super(rule, e, rejectedValues);
    }
}

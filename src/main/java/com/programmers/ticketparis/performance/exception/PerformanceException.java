package com.programmers.ticketparis.performance.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class PerformanceException extends BusinessException {

    public PerformanceException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

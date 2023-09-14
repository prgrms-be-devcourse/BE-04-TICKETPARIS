package com.programmers.ticketparis.exception;

import java.util.List;

public class PerformanceException extends BusinessException {

    public PerformanceException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule, rejectedValues);
    }
}

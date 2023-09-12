package com.programmers.ticketparis.exception;

import java.util.List;

public class CustomerException extends BusinessException {
    public CustomerException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule, rejectedValues);
    }
}

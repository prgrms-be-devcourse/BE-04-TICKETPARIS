package com.programmers.ticketparis.exception;

import java.util.List;

public class CommonException extends BusinessException {

    public CommonException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule, rejectedValues);
    }
}

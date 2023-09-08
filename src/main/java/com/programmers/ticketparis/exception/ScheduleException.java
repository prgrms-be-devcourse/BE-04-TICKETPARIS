package com.programmers.ticketparis.exception;

import java.util.List;

public class ScheduleException extends BusinessException {

    public ScheduleException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule, rejectedValues);
    }
}

package com.programmers.ticketparis.exception;

public class ScheduleException extends BusinessException {

    public ScheduleException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

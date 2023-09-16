package com.programmers.ticketparis.schedule.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class ScheduleException extends BusinessException {

    public ScheduleException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

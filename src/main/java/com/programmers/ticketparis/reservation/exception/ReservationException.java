package com.programmers.ticketparis.reservation.exception;

import com.programmers.ticketparis.common.exception.BusinessException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

public class ReservationException extends BusinessException {

    public ReservationException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

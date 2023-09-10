package com.programmers.ticketparis.exception;

public class ReservationException extends BusinessException {

    public ReservationException(ExceptionRule rule, Object... rejectedValues) {
        super(rule, rejectedValues);
    }
}

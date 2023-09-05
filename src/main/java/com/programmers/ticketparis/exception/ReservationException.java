package com.programmers.ticketparis.exception;

import java.util.List;

public class ReservationException extends BusinessException {

    public ReservationException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule, rejectedValues);
    }
}

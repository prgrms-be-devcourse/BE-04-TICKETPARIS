package com.programmers.ticketparis.exception;

import java.util.List;

public class SellerException extends BusinessException {

    public SellerException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule, rejectedValues);
    }
}

package com.programmers.ticketparis.exception;

import java.util.List;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ExceptionRule rule;
    private final List<String> rejectedValues;

    protected BusinessException(ExceptionRule rule, List<String> rejectedValues) {
        super(rule.getMessage());
        this.rule = rule;
        this.rejectedValues = rejectedValues;
    }
}

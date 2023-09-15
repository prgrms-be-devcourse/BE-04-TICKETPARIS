package com.programmers.ticketparis.dto;

import java.util.List;

import com.programmers.ticketparis.exception.ExceptionRule;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorData {

    private ExceptionRule exceptionRule;
    private List<String> rejectedValues;

    @Builder
    private ErrorData(ExceptionRule exceptionRule, List<String> rejectedValues) {
        this.exceptionRule = exceptionRule;
        this.rejectedValues = rejectedValues;
    }
}

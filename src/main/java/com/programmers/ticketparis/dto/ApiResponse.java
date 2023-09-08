package com.programmers.ticketparis.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private T data;

    @Builder
    public ApiResponse(String path, T data) {
        this.path = path;
        this.data = data;
    }
}

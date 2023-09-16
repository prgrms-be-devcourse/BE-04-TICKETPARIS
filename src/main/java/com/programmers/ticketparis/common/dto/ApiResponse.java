package com.programmers.ticketparis.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime localDateTime = LocalDateTime.now();

    private final String path;
    private final T data;
    private final String message;

    @Builder
    private ApiResponse(String path, T data, String message) {
        this.path = path;
        this.data = data;
        this.message = message;
    }
}

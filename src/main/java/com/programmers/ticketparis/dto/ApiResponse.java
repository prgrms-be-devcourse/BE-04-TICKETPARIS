package com.programmers.ticketparis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(staticName = "of")
public class ApiResponse<T> {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private T data;
}

package com.programmers.ticketparis.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ApiResponse<T> {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private T data;
}

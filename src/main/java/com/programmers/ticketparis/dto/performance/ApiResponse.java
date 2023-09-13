package com.programmers.ticketparis.dto.performance;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ApiResponse<T> {

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private String path;
    private T data;
}

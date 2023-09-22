package com.programmers.ticketparis.auth.util;

import com.programmers.ticketparis.auth.dto.SessionValueDto;

public class SessionThreadLocal {

    private static final ThreadLocal<SessionValueDto> sessionValueThreadLocal = new ThreadLocal<>();

    public static SessionValueDto getSessionValueDto() {
        return sessionValueThreadLocal.get();
    }

    public static void setSessionValueDto(SessionValueDto sessionValueDto) {
        sessionValueThreadLocal.set(sessionValueDto);
    }

    public static void clear() {
        sessionValueThreadLocal.remove();
    }
}

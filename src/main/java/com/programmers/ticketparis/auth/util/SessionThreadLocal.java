package com.programmers.ticketparis.auth.util;

import com.programmers.ticketparis.auth.dto.Session;

public class SessionThreadLocal {

    private static final ThreadLocal<Session> sessionValueThreadLocal = new ThreadLocal<>();

    public static Session getSessionValueDto() {
        return sessionValueThreadLocal.get();
    }

    public static void setSessionValueDto(Session loggedInMemberInfo) {
        sessionValueThreadLocal.set(loggedInMemberInfo);
    }

    public static void clear() {
        sessionValueThreadLocal.remove();
    }
}

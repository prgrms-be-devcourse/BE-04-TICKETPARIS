package com.programmers.ticketparis.auth.util;

import com.programmers.ticketparis.auth.dto.LoggedinMemberInfo;

public class SessionThreadLocal {

    private static final ThreadLocal<LoggedinMemberInfo> sessionValueThreadLocal = new ThreadLocal<>();

    public static LoggedinMemberInfo getSessionValueDto() {
        return sessionValueThreadLocal.get();
    }

    public static void setSessionValueDto(LoggedinMemberInfo loggedInMemberInfo) {
        sessionValueThreadLocal.set(loggedInMemberInfo);
    }

    public static void clear() {
        sessionValueThreadLocal.remove();
    }
}

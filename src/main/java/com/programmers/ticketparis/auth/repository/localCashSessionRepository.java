package com.programmers.ticketparis.auth.repository;

import static com.programmers.ticketparis.common.util.SessionConst.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.programmers.ticketparis.auth.dto.SessionValueDto;
import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class localCashSessionRepository implements SessionRepository {

    private Map<String, SessionValueDto> sessionLocalCash = new ConcurrentHashMap<>();

    public void createSession(SessionValueDto value, HttpServletResponse httpServletResponse) {
        String sessionId = UUID.randomUUID().toString();
        sessionLocalCash.put(sessionId, value);

        httpServletResponse.addCookie(new Cookie(SESSION_COOKIE_NAME, sessionId));
    }

    public SessionValueDto getSessionOrNull(HttpServletRequest httpServletRequest) {
        Cookie sessionCookie = findCookieOrNull(httpServletRequest);
        if (sessionCookie == null) {
            return null;
        }

        return sessionLocalCash.get(sessionCookie.getValue());
    }

    public void expire(HttpServletRequest httpServletRequest) {
        Cookie sessionCookie = findCookieOrNull(httpServletRequest);
        if (sessionCookie == null || (!sessionLocalCash.containsKey(sessionCookie.getValue()))) {
            throw new AuthException(ExceptionRule.LOGOUT_FAILED);
        }

        sessionLocalCash.remove(sessionCookie.getValue());
    }

    private Cookie findCookieOrNull(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getCookies() == null) {
            return null;
        }

        return Arrays.stream(httpServletRequest.getCookies())
            .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
            .findAny()
            .orElse(null);
    }
}

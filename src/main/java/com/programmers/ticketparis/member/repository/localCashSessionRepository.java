package com.programmers.ticketparis.member.repository;

import static com.programmers.ticketparis.common.util.SessionConst.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.dto.SessionValueDto;
import com.programmers.ticketparis.member.exception.AuthException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class localCashSessionRepository implements SessionRepository {

    private Map<String, SessionValueDto> sessionStore = new ConcurrentHashMap<>();

    public void createSession(SessionValueDto value, HttpServletResponse httpServletResponse) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        httpServletResponse.addCookie(new Cookie(SESSION_COOKIE_NAME, sessionId));
    }

    public SessionValueDto getSession(HttpServletRequest httpServletRequest) {
        Cookie sessionCookie = findCookie(httpServletRequest);

        return sessionStore.get(sessionCookie.getValue());
    }

    public void expire(HttpServletRequest httpServletRequest) {
        Cookie sessionCookie = findCookie(httpServletRequest);
        sessionStore.remove(sessionCookie.getValue());
    }

    private Cookie findCookie(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getCookies() == null) {
            throw new AuthException(ExceptionRule.AUTHENTICATION_FAILED_COOKIE_NOT_EXIST, "Cookie Not Found");
        }

        return Arrays.stream(httpServletRequest.getCookies())
            .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
            .findAny()
            .orElseThrow(
                () -> new AuthException(ExceptionRule.AUTHENTICATION_FAILED_COOKIE_NOT_EXIST, "Cookie Not Found")
            );
    }
}

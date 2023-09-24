package com.programmers.ticketparis.auth.repository;

import static com.programmers.ticketparis.common.util.SessionConst.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.programmers.ticketparis.auth.dto.Session;
import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.common.exception.ExceptionRule;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class localCashSessionRepository implements SessionRepository {

    private Map<String, Session> sessionLocalCash = new ConcurrentHashMap<>();

    public String createSession(Session session) {
        String sessionId = UUID.randomUUID().toString();
        sessionLocalCash.put(sessionId, session);

        return sessionId;
    }

    public Session getSession(String sessionId) {
        if (!sessionLocalCash.containsKey(sessionId)) {
            throw new AuthException(ExceptionRule.AUTHENTICATION_FAILED);
        }

        Session currentSession = sessionLocalCash.get(sessionId);
        currentSession.updateLastAccessTime();

        return currentSession;
    }

    public void expire(String sessionId) {
        if (!sessionLocalCash.containsKey(sessionId)) {
            throw new AuthException(ExceptionRule.LOGOUT_FAILED);
        }

        sessionLocalCash.remove(sessionId);
    }

    //1. 요청에 SESSION_COOKIE_NAME 쿠키가 담겨 있는지 체크 후, 가져온다.
    private Cookie findSessionCookieFromRequestOrNull(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getCookies() == null) {
            return null;
        }

        return Arrays.stream(httpServletRequest.getCookies())
            .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
            .findAny()
            .orElse(null);
    }
}

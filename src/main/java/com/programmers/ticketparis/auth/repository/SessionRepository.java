package com.programmers.ticketparis.auth.repository;

import com.programmers.ticketparis.auth.dto.Session;

public interface SessionRepository {

    String createSession(Session value);

    Session getSession(String sessionId);

    void expire(String sessionId);

}



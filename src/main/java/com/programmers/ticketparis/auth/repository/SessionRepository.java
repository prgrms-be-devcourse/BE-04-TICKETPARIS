package com.programmers.ticketparis.auth.repository;

import com.programmers.ticketparis.auth.dto.SessionValueDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SessionRepository {

    void createSession(SessionValueDto value, HttpServletResponse httpServletResponse);

    SessionValueDto getSessionOrNull(HttpServletRequest httpServletRequest);

    void expire(HttpServletRequest httpServletRequest);

}



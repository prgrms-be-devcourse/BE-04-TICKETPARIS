package com.programmers.ticketparis.member.repository;

import com.programmers.ticketparis.member.dto.SessionValueDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SessionRepository {

    void createSession(SessionValueDto value, HttpServletResponse httpServletResponse);

    Object getSession(HttpServletRequest httpServletRequest);

    void expire(HttpServletRequest httpServletRequest);

}



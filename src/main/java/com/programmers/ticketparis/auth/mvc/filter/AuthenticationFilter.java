package com.programmers.ticketparis.auth.mvc.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;

import com.programmers.ticketparis.auth.dto.SessionValueDto;
import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.auth.service.AuthService;
import com.programmers.ticketparis.common.exception.ExceptionRule;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private static final String[] whitelist = {"/api/customers", "/api/customers/login", "/api/sellers",
        "/api/sellers/login", "/api/logout"};

    private final AuthService authService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
        IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        String requestURI = httpServletRequest.getRequestURI();

        if (isLoginCheckPath(requestURI)) {
            SessionValueDto sessionValueDto = authService.getSessionOrNull(httpServletRequest);
            if (sessionValueDto == null) {
                throw new AuthException(ExceptionRule.AUTHENTICATION_FAILED);
            }
        }

        chain.doFilter(request, response);
    }

    //화이트 리스트의 경우 인증 체크X
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}

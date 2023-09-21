package com.programmers.ticketparis.auth.mvc.filter;

import java.io.IOException;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.common.dto.ApiResponse;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
        IOException,
        ServletException {
        try {
            chain.doFilter(request, response);
        } catch (AuthException e) {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            HttpServletResponse httpServletResponse = (HttpServletResponse)response;

            setAuthExceptionResponse(httpServletRequest, httpServletResponse, e);
        }
    }

    private void setAuthExceptionResponse(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, AuthException e) throws
        IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(e.getExceptionRule().getStatus().value());

        try (ServletOutputStream outputStream = httpServletResponse.getOutputStream()) {
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                .path(httpServletRequest.getRequestURI())
                .message(e.getExceptionRule().getMessage())
                .build();

            ObjectMapper objectMapper = new ObjectMapper();
            //(ApiResponse 필드인 TimeStamp 해석을 위한 모듈 등록)
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(outputStream, apiResponse);

            outputStream.flush();
        }
    }
}

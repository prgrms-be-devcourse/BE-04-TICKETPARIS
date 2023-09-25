package com.programmers.ticketparis.auth.mvc.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.common.dto.ApiResponseType;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ExceptionHandlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
        IOException,
        ServletException {
        try {
            chain.doFilter(request, response);
        } catch (AuthException e) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            setAuthExceptionResponse(httpServletRequest, httpServletResponse, e);
        }
    }

    private void setAuthExceptionResponse(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse, AuthException e) throws
        IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(e.getExceptionRule().getStatus().value());

        try (ServletOutputStream outputStream = httpServletResponse.getOutputStream()) {
            ApiResponseType<Object> apiResponseType = ApiResponseType.builder()
                .path(httpServletRequest.getRequestURI())
                .message(e.getExceptionRule().getMessage())
                .build();

            ObjectMapper objectMapper = new ObjectMapper();
            //(ApiResponseType 필드인 TimeStamp 해석을 위한 모듈 등록)
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(outputStream, apiResponseType);

            outputStream.flush();
        }
    }
}

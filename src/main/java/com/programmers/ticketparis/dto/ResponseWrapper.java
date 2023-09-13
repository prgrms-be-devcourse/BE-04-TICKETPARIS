package com.programmers.ticketparis.dto;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.programmers.ticketparis.exception.ExceptionRule;

@RestControllerAdvice(basePackages = "com.programmers.ticketparis.controller")
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        String path = request.getURI().getPath();

        if (body instanceof ErrorData errorData) {
            ExceptionRule exceptionRule = errorData.getExceptionRule();
            response.setStatusCode(exceptionRule.getStatus());

            return ApiResponse.builder()
                .path(path)
                .data(errorData.getRejectedValues())
                .message(exceptionRule.getMessage())
                .build();
        }

        return ApiResponse.builder()
            .path(path)
            .data(body)
            .build();
    }
}

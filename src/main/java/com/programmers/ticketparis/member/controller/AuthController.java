package com.programmers.ticketparis.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.member.dto.request.LoginForm;
import com.programmers.ticketparis.member.dto.response.EmptyResponse;
import com.programmers.ticketparis.member.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/customers/login")
    @ResponseStatus(HttpStatus.CREATED)
    public EmptyResponse customerLogin(@Valid @RequestBody LoginForm loginForm,
        HttpServletResponse httpServletResponse) {
        authService.customerLogin(loginForm, httpServletResponse);

        return new EmptyResponse();
    }

    @PostMapping("/sellers/login")
    @ResponseStatus(HttpStatus.CREATED)
    public EmptyResponse sellerLogin(@Valid @RequestBody LoginForm loginForm,
        HttpServletResponse httpServletResponse) {
        authService.sellerLogin(loginForm, httpServletResponse);

        return new EmptyResponse();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse logout(HttpServletRequest httpServletRequest) {
        authService.logout(httpServletRequest);

        return new EmptyResponse();
    }
}

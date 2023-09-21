package com.programmers.ticketparis.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.auth.dto.LoginRequest;
import com.programmers.ticketparis.auth.dto.LoginSuccessResponse;
import com.programmers.ticketparis.auth.dto.LogoutSuccessResponse;
import com.programmers.ticketparis.auth.service.AuthService;

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
    public LoginSuccessResponse customerLogin(@Valid @RequestBody LoginRequest loginRequest,
        HttpServletResponse httpServletResponse) {
        authService.customerLogin(loginRequest, httpServletResponse);

        return new LoginSuccessResponse();
    }

    @PostMapping("/sellers/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginSuccessResponse sellerLogin(@Valid @RequestBody LoginRequest loginRequest,
        HttpServletResponse httpServletResponse) {
        authService.sellerLogin(loginRequest, httpServletResponse);

        return new LoginSuccessResponse();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public LogoutSuccessResponse logout(HttpServletRequest httpServletRequest) {
        authService.logout(httpServletRequest);

        return new LogoutSuccessResponse();
    }
}

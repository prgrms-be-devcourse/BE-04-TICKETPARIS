package com.programmers.ticketparis.auth.controller;

import com.programmers.ticketparis.auth.dto.LoginRequest;
import com.programmers.ticketparis.auth.dto.LoginSuccessResponse;
import com.programmers.ticketparis.auth.dto.LogoutSuccessResponse;
import com.programmers.ticketparis.auth.service.AuthService;
import com.programmers.ticketparis.common.dto.ApiResponseType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Login 권한 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/customers/login")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "고객 로그인 성공",
        description = "고객 로그인 성공 응답 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "로그인 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", description = "로그인 권한 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public LoginSuccessResponse customerLogin(@Valid @RequestBody LoginRequest loginRequest,
                                              HttpServletResponse httpServletResponse) {
        authService.customerLogin(loginRequest, httpServletResponse);

        return new LoginSuccessResponse();
    }

    @PostMapping("/sellers/login")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "판매자 로그인 성공",
        description = "판매자 로그인 성공 응답 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "로그인 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", description = "로그인 권한 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public LoginSuccessResponse sellerLogin(@Valid @RequestBody LoginRequest loginRequest,
                                            HttpServletResponse httpServletResponse) {
        authService.sellerLogin(loginRequest, httpServletResponse);

        return new LoginSuccessResponse();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "로그아웃",
        description = "로그아웃 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그아웃 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", description = "로그아웃 권한 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public LogoutSuccessResponse logout(HttpServletRequest httpServletRequest) {
        authService.logout(httpServletRequest);

        return new LogoutSuccessResponse();
    }
}

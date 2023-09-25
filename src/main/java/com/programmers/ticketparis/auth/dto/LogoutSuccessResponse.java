package com.programmers.ticketparis.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "logoutSuccess", description = "로그아웃 성공 시 응답")
public class LogoutSuccessResponse {

    @Schema(description = "로그아웃 여부")
    private String logout = "로그아웃 성공";
}

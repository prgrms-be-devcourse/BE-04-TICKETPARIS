package com.programmers.ticketparis.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "loginSuccess", description = "로그인 성공 시 응답")
public class LoginSuccessResponse {

    @Schema(description = "로그인 여부")
    private String login = "로그인 성공";
}

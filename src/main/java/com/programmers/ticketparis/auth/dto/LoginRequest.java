package com.programmers.ticketparis.auth.dto;

import com.programmers.ticketparis.member.dto.validator.PasswordValid;
import com.programmers.ticketparis.member.dto.validator.UsernameValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "로그인 요청")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @Schema(description = "아이디")
    @UsernameValid(message = "아이디는 8자 이상 15자 이하(영어, 숫자, 공백불가)")
    private String username;

    @Schema(description = "비밀번호")
    @PasswordValid(message = "비밀번호는 8자 이상 20자 이하 (<영어, 숫자, 특수문자> 포함, 공백불가)")
    private String password;

    private LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static LoginRequest of(String username, String password) {
        return new LoginRequest(username, password);
    }
}

package com.programmers.ticketparis.member.dto;

import com.programmers.ticketparis.member.dto.validator.PasswordValid;
import com.programmers.ticketparis.member.dto.validator.UsernameValid;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerLoginForm {

    @UsernameValid(message = "아이디는 8자 이상 15자 이하(영어, 숫자, 공백불가)")
    private String username;

    @PasswordValid(message = "비밀번호는 8자 이상 20자 이하 (<영어, 숫자, 특수문자> 포함, 공백불가)")
    private String password;

    private CustomerLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

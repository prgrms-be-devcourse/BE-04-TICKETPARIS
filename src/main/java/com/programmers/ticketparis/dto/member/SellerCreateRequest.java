package com.programmers.ticketparis.dto.member;

import com.programmers.ticketparis.domain.member.Seller;
import com.programmers.ticketparis.dto.member.validator.EmailValid;
import com.programmers.ticketparis.dto.member.validator.PasswordValid;
import com.programmers.ticketparis.dto.member.validator.PhoneValid;
import com.programmers.ticketparis.dto.member.validator.RegistrationNumberValid;
import com.programmers.ticketparis.dto.member.validator.UsernameValid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerCreateRequest {

    @UsernameValid(message = "아이디는 8자 이상 15자 이하(영어, 숫자, 공백불가)")
    private String username;

    @PasswordValid(message = "비밀번호는 8자 이상 20자 이하 (<영어, 숫자, 특수문자> 포함, 공백불가)")
    private String password;

    @NotNull
    @Size(min = 0, max = 50, message = "이름은 50자 이하")
    private String name;

    @EmailValid(message = "이메일은 320자 이하(공백 미포함)")
    private String email;

    @PhoneValid(message = "핸드폰 번호는 다음 형식(000-0000-0000)에 맞추어 입력")
    private String phone;

    @NotNull
    @RegistrationNumberValid
    private String registrationNumber;

    @NotNull
    @Size(min = 1, max = 100, message = "주소는 1자 이상, 100자 이하로 입력")
    private String storeName;

    private SellerCreateRequest(String username, String password, String name, String email, String phone,
        String registrationNumber, String storeName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationNumber = registrationNumber;
        this.storeName = storeName;
    }

    public Seller toEntity() {
        return Seller.builder()
            .username(username)
            .password(password)
            .name(name)
            .email(email)
            .phone(phone)
            .registrationNumber(registrationNumber)
            .storeName(storeName)
            .build();
    }
}

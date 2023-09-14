package com.programmers.ticketparis.dto.member;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.programmers.ticketparis.domain.member.Customer;
import com.programmers.ticketparis.dto.member.validator.EmailValid;
import com.programmers.ticketparis.dto.member.validator.PasswordValid;
import com.programmers.ticketparis.dto.member.validator.PhoneValid;
import com.programmers.ticketparis.dto.member.validator.UsernameValid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerCreateRequest {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull
    @Size(min = 0, max = 255, message = "주소는 255자 이하로 입력해주세요")
    private String address;

    private CustomerCreateRequest(String username, String password, String name, String email, String phone,
        LocalDate birthDate, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Customer toEntity() {
        return Customer.builder()
            .username(username)
            .password(password)
            .name(name)
            .email(email)
            .birthDate(birthDate)
            .phone(phone)
            .address(address)
            .build();
    }
}

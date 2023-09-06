package com.programmers.ticketparis.dto.member;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.programmers.ticketparis.domain.member.Customer;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerCreateRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "아이디는 8자 이상 15자 이하로 입력해주세요. (영어, 숫자, 공백불가)")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "비밀번호는 8자 이상 20자 이하로 입력해주세요. (<영어, 숫자, 특수문자> 포함, 공백불가)")
    private String password;

    @Size(min = 0, max = 50, message = "이름은 50자 이하로 입력해주세요.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{1,255}\\.[a-zA-Z]{2,}$", message = "이메일은 320자 이하로 입력해주세요.(공백 미포함)")
    private String email;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "핸드폰 번호 형식(000-0000-0000)에 맞추어 입력해주세요.")
    private String phone;

    @Size(min = 0, max = 255, message = "주소는 255자 이하로 입력해주세요")
    private String address;

    @Builder
    private CustomerCreateRequest(String username, String password, String name, String email, LocalDate birthDate, String phone, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    public Customer toEntity() {
        return Customer.builder()
            .username(this.username)
            .password(this.password)
            .name(this.name)
            .email(this.email)
            .birthDate(this.birthDate)
            .phone(this.phone)
            .address(this.address)
            .build();
    }
}

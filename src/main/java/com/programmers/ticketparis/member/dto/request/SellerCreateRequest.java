package com.programmers.ticketparis.member.dto.request;

import com.programmers.ticketparis.member.domain.Seller;
import com.programmers.ticketparis.member.dto.validator.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "판매자 생성 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellerCreateRequest {

    @Schema(description = "아이디")
    @UsernameValid(message = "아이디는 8자 이상 15자 이하(영어, 숫자, 공백불가)로 입력")
    private String username;

    @Schema(description = "비밀번호")
    @PasswordValid(message = "비밀번호는 8자 이상 20자 이하(<영어, 숫자, 특수문자> 포함, 공백불가)로 입력")
    private String password;

    @NotNull
    @Schema(description = "이름")
    @Size(min = 0, max = 50, message = "이름은 50자 이하로 입력")
    private String name;

    @Schema(description = "이메일")
    @EmailValid(message = "이메일은 320자 이하(공백 미포함)로 입력")
    private String email;

    @Schema(description = "핸드폰 번호")
    @PhoneValid(message = "핸드폰 번호는 다음 형식(000-0000-0000)으로 입력")
    private String phone;

    @NotNull
    @RegistrationNumberValid
    @Schema(description = "사업자 등록번호")
    private String registrationNumber;

    @NotNull
    @Schema(description = "상호명")
    @Size(min = 1, max = 100, message = "주소는 255자 이하로 입력")
    private String storeName;

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

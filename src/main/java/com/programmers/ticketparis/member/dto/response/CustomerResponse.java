package com.programmers.ticketparis.member.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.member.domain.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Schema(description = "고객 조회 시 응답 json data 필드값")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerResponse {

    @Schema(description = "아이디")
    private Long customerId;

    @Schema(description = "비밀번호")
    private String username;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "생년월일")
    private LocalDate birthDate;

    @Schema(description = "핸드폰 번호")
    private String phone;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "고객 생성 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @Schema(description = "고객 수정 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

    private CustomerResponse(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.username = customer.getUsername();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.birthDate = customer.getBirthDate();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
        this.createdDatetime = customer.getCreatedDatetime();
        this.updatedDatetime = customer.getUpdatedDatetime();
    }

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer);
    }
}

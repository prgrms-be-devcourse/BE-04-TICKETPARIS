package com.programmers.ticketparis.member.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.member.domain.Customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerResponse {

    private Long customerId;
    private String username;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

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

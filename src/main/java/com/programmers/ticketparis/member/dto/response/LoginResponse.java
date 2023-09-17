package com.programmers.ticketparis.member.dto.response;

import com.programmers.ticketparis.member.domain.Customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private Long customerId;
    private String username;
    private String name;

    private LoginResponse(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.username = customer.getUsername();
        this.name = customer.getName();
    }

    public static LoginResponse from(Customer customer) {
        return new LoginResponse(customer);
    }
}

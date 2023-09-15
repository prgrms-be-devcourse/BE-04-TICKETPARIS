package com.programmers.ticketparis.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.member.CustomerCreateRequest;
import com.programmers.ticketparis.dto.member.CustomerIdResponse;
import com.programmers.ticketparis.dto.member.CustomerResponse;
import com.programmers.ticketparis.service.member.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerIdResponse createAccount(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.createAccount(customerCreateRequest);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse findCustomerById(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId);
    }
}

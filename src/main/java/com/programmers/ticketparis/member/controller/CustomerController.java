package com.programmers.ticketparis.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.member.dto.CustomerLoginForm;
import com.programmers.ticketparis.member.dto.request.CustomerCreateRequest;
import com.programmers.ticketparis.member.dto.response.CustomerIdResponse;
import com.programmers.ticketparis.member.dto.response.CustomerResponse;
import com.programmers.ticketparis.member.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerIdResponse createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.createCustomer(customerCreateRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse login(@Valid @RequestBody CustomerLoginForm customerLoginForm) {
        CustomerResponse loginCustomerResponse = customerService.login(customerLoginForm);

        //todo: 로그인 성공 처리

        return loginCustomerResponse;
    }

    @GetMapping("/{customerId}")
    public CustomerResponse findCustomerById(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId);
    }
}

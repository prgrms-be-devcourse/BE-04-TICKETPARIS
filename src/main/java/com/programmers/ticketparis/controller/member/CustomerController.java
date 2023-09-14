package com.programmers.ticketparis.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.ApiResponse;
import com.programmers.ticketparis.dto.member.CustomerCreateRequest;
import com.programmers.ticketparis.dto.member.CustomerIdResponse;
import com.programmers.ticketparis.dto.member.CustomerResponse;
import com.programmers.ticketparis.service.member.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerIdResponse> createAccount(
        @Valid @RequestBody CustomerCreateRequest customerCreateRequest,
        HttpServletRequest httpServletRequest
    ) {
        CustomerIdResponse customerIdResponse = customerService.createAccount(customerCreateRequest);

        return ApiResponse.of(httpServletRequest.getRequestURI(), customerIdResponse);
    }

    @GetMapping("/{customerId}")
    public ApiResponse<CustomerResponse> findCustomerById(@PathVariable Long customerId,
        HttpServletRequest httpServletRequest
    ) {
        CustomerResponse customerResponse = customerService.findCustomerById(customerId);

        return ApiResponse.of(httpServletRequest.getRequestURI(), customerResponse);
    }
}

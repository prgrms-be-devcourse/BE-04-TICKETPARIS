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
import com.programmers.ticketparis.dto.member.CustomerResponse;
import com.programmers.ticketparis.service.member.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        log.info("컨트롤러 도착");
        customerService.createAccount(customerCreateRequest);
    }

    @GetMapping("/{customerId}")
    public ApiResponse<CustomerResponse> findById(@PathVariable("customerId") Long customerId, HttpServletRequest httpServletRequest) {
        return ApiResponse.of(httpServletRequest.getRequestURI(), customerService.findById(customerId));
    }
}

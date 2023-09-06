package com.programmers.ticketparis.service.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.member.Customer;
import com.programmers.ticketparis.dto.member.CustomerCreateRequest;
import com.programmers.ticketparis.dto.member.CustomerResponse;
import com.programmers.ticketparis.exception.CustomerException;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.repository.member.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void createAccount(CustomerCreateRequest customerCreateRequest) {
        log.info("서비스 도착");
        Customer customer = customerCreateRequest.toEntity();
        customerRepository.createAccount(customer);
        log.info("서비스 통과");
    }

    public CustomerResponse findById(Long customerId) {
        existById(customerId);
        return customerRepository.findById(customerId)
            .map(CustomerResponse::new)
            .orElseThrow(() -> new CustomerException(ExceptionRule.NOT_EXIST_CUSTOMER, List.of(String.valueOf(customerId))));
    }

    private void existById(Long customerId) {
        if (!customerRepository.existById(customerId)) {
            throw new CustomerException(ExceptionRule.NOT_EXIST_CUSTOMER, List.of(String.valueOf(customerId)));
        }
    }
}

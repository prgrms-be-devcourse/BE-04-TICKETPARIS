package com.programmers.ticketparis.member.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.member.domain.Customer;
import com.programmers.ticketparis.member.dto.request.CustomerCreateRequest;
import com.programmers.ticketparis.member.dto.response.CustomerIdResponse;
import com.programmers.ticketparis.member.dto.response.CustomerResponse;
import com.programmers.ticketparis.member.exception.CustomerException;
import com.programmers.ticketparis.member.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerIdResponse createCustomer(CustomerCreateRequest customerCreateRequest) {
        validateCustomerNotExistsByUserNameOrEmail(
            customerCreateRequest.getUsername(),
            customerCreateRequest.getEmail()
        );

        Customer customer = customerCreateRequest.toEntity();
        Long customerId = customerRepository.save(customer);

        return CustomerIdResponse.from(customerId);
    }

    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username)
            .orElseThrow(() -> new CustomerException(LOGIN_FAILED_USERNAME_NOT_EXIST, username));
    }

    public CustomerResponse findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
            .map(CustomerResponse::from)
            .orElseThrow(() -> new CustomerException(CUSTOMER_NOT_EXIST, customerId));
    }

    private void validateCustomerNotExistsByUserNameOrEmail(String username, String email) {
        if (customerRepository.existsByUsernameOrEmail(username, email)) {
            throw new CustomerException(CUSTOMER_ALREADY_EXIST, username, email);
        }
    }

    //todo: 추후 [명세서 우선순위:2] 기능인 update, delete 메서드 구현되면 해당 메서드 활용
    private void validateCustomerExists(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerException(CUSTOMER_NOT_EXIST, customerId);
        }
    }
}

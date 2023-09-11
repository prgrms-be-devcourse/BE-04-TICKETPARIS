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

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long createAccount(CustomerCreateRequest customerCreateRequest) {
        validateCustomerNotExistsByUserNameOrEmail(customerCreateRequest.getUsername(),
            customerCreateRequest.getEmail());

        Customer customer = customerCreateRequest.toEntity();
        return customerRepository.save(customer);
    }

    public CustomerResponse findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
            .map(CustomerResponse::from)
            .orElseThrow(
                () -> new CustomerException(ExceptionRule.NOT_EXIST_CUSTOMER, List.of(String.valueOf(customerId))));
    }

    private void validateCustomerNotExistsByUserNameOrEmail(String username, String email) {
        if (customerRepository.existsByUsernameOrEmail(username, email)) {
            throw new CustomerException(ExceptionRule.CUSTOMER_ALREADY_CREATED, List.of(username, email));
        }
    }

    //todo: 추후 [명세서 우선순위:2] 기능인 update, delete 메서드 구현되면 해당 메서드 활용
    private void validateCustomerExists(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerException(ExceptionRule.NOT_EXIST_CUSTOMER, List.of(String.valueOf(customerId)));
        }
    }
}

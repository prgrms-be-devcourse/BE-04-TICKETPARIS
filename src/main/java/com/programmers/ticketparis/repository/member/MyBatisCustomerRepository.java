package com.programmers.ticketparis.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.member.Customer;
import com.programmers.ticketparis.exception.CustomerException;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.mapper.member.CustomerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisCustomerRepository implements CustomerRepository {

    private final CustomerMapper customerMapper;

    @Override
    public void createAccount(Customer customer) {
        try {
            customerMapper.createAccount(customer);
        } catch (DuplicateKeyException e) {
            if (e.getMessage().contains("USERNAME NULLS FIRST")) {
                throw new CustomerException(ExceptionRule.CUSTOMER_ALREADY_REGISTERED, List.of(customer.getUsername()));
            } else {
                throw new CustomerException(ExceptionRule.CUSTOMER_ALREADY_REGISTERED, List.of(customer.getEmail()));
            }
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerMapper.findById(id);
    }

    @Override
    public boolean existById(Long customerId) {
        return customerMapper.existById(customerId);
    }

}

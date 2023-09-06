package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.member.Customer;
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
        customerMapper.createAccount(customer);
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

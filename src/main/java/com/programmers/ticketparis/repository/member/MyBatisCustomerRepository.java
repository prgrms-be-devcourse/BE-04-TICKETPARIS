package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.member.Customer;
import com.programmers.ticketparis.mapper.member.CustomerMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCustomerRepository implements CustomerRepository {

    private final CustomerMapper customerMapper;

    @Override
    public Long save(Customer customer) {
        return customerMapper.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerMapper.findById(id);
    }

    @Override
    public Boolean existsById(Long customerId) {
        return customerMapper.existsById(customerId);
    }

    @Override
    public Boolean existsByUsernameOrEmail(String username, String email) {
        return customerMapper.existsByUsernameOrEmail(username, email);
    }
}

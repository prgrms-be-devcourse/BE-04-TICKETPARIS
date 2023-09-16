package com.programmers.ticketparis.member.repository.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.member.domain.Customer;
import com.programmers.ticketparis.member.repository.CustomerRepository;
import com.programmers.ticketparis.member.repository.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisCustomerRepository implements CustomerRepository {

    private final CustomerMapper customerMapper;

    @Override
    public Long save(Customer customer) {
        customerMapper.save(customer);

        return customer.getCustomerId();
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return customerMapper.findById(customerId);
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

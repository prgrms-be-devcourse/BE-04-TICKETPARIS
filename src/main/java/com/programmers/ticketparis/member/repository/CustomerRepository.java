package com.programmers.ticketparis.member.repository;

import java.util.Optional;

import com.programmers.ticketparis.member.domain.Customer;

public interface CustomerRepository {

    Long save(Customer item);

    Optional<Customer> findById(Long customerId);

    Optional<Customer> findByUsername(String userName);

    Boolean existsById(Long customerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

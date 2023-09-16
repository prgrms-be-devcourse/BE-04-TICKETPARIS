package com.programmers.ticketparis.member.repository;

import java.util.Optional;

import com.programmers.ticketparis.member.domain.Customer;

public interface CustomerRepository {

    Long save(Customer item);

    Optional<Customer> findById(Long customerId);

    Boolean existsById(Long customerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

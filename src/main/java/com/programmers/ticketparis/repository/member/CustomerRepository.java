package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import com.programmers.ticketparis.domain.member.Customer;

public interface CustomerRepository {

    Long save(Customer item);

    Optional<Customer> findById(Long customerId);

    Boolean existsById(Long customerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

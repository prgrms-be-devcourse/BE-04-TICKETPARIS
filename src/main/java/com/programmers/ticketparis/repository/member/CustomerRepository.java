package com.programmers.ticketparis.repository.member;

import java.util.List;
import java.util.Optional;

import com.programmers.ticketparis.domain.member.Customer;

public interface CustomerRepository {
	Customer save(Customer item);

	Optional<Customer> findById(Long id);

	List<Customer> findAll();
}

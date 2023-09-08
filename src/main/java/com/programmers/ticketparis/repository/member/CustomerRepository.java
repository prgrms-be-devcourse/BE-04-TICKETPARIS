package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import com.programmers.ticketparis.domain.member.Customer;

public interface CustomerRepository {
	void createAccount(Customer item);

	Optional<Customer> findById(Long id);

	boolean existById(Long customerId);

}

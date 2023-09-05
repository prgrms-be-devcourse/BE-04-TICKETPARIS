package com.programmers.ticketparis.repository.member;

import java.util.List;
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
	public Customer save(Customer customer) {
		customerMapper.save(customer);
		return customer;
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerMapper.findById(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerMapper.findAll();
	}
}

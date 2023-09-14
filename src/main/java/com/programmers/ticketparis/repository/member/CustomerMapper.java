package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.member.Customer;

@Mapper
public interface CustomerMapper {

    Long save(Customer item);

    Optional<Customer> findById(Long customerId);

    Boolean existsById(Long customerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

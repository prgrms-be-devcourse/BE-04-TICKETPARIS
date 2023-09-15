package com.programmers.ticketparis.member.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.member.domain.Customer;

@Mapper
public interface CustomerMapper {

    void save(Customer item);

    Optional<Customer> findById(Long customerId);

    Optional<Customer> findByUsername(String userName);

    Boolean existsById(Long customerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

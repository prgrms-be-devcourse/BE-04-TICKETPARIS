package com.programmers.ticketparis.mapper.member;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.member.Customer;

@Mapper
public interface CustomerMapper {

    Long save(Customer item);

    Optional<Customer> findById(Long id);

    Boolean existsById(Long customerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

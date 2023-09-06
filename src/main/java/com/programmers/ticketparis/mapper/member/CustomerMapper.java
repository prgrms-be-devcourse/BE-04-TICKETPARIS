package com.programmers.ticketparis.mapper.member;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.member.Customer;

@Mapper
public interface CustomerMapper {
    void createAccount(Customer item);

    Optional<Customer> findById(Long id);

    boolean existById(Long customerId);
}

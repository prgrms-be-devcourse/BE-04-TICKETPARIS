package com.programmers.ticketparis.member.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.member.domain.Seller;

@Mapper
public interface SellerMapper {

    void save(Seller item);

    Optional<Seller> findById(Long sellerId);

    Optional<Seller> findByUsername(String userName);

    Boolean existsById(Long sellerId);

    Boolean existsByUniqueFields(String username, String email, String registrationNumber);
}

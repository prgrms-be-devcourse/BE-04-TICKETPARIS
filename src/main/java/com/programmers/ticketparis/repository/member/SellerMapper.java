package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.member.Seller;

@Mapper
public interface SellerMapper {

    Long save(Seller item);

    Optional<Seller> findById(Long sellerId);

    Boolean existsById(Long sellerId);

    Boolean existsByUniqueFields(String username, String email, String registrationNumber);
}

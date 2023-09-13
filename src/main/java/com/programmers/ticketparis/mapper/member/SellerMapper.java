package com.programmers.ticketparis.mapper.member;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.member.Seller;

@Mapper
public interface SellerMapper {

    Long save(Seller item);

    Optional<Seller> findById(Long id);

    Boolean existsById(Long sellerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

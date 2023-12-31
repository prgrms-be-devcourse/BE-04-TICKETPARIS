package com.programmers.ticketparis.member.repository;

import java.util.Optional;

import com.programmers.ticketparis.member.domain.Seller;

public interface SellerRepository {

    Long save(Seller item);

    Optional<Seller> findById(Long sellerId);

    Optional<Seller> findByUsername(String userName);

    Boolean existsById(Long sellerId);

    Boolean existsByUniqueFields(String username, String email, String registrationNumber);

}

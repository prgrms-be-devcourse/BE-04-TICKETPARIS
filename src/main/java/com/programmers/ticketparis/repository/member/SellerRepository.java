package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import com.programmers.ticketparis.domain.member.Seller;

public interface SellerRepository {

    Long save(Seller item);

    Optional<Seller> findById(Long sellerId);

    Boolean existsById(Long sellerId);

    Boolean existsByUniqueFields(String username, String email, String registrationNumber);
}

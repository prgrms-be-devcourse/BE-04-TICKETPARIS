package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import com.programmers.ticketparis.domain.member.Seller;

public interface SellerRepository {

    Long save(Seller item);

    Optional<Seller> findById(Long id);

    Boolean existsById(Long sellerId);

    Boolean existsByUsernameOrEmail(String username, String email);
}

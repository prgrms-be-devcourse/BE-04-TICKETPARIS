package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.member.Seller;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisSellerRepository implements SellerRepository {

    private final SellerMapper sellerMapper;

    @Override
    public Long save(Seller seller) {
        sellerMapper.save(seller);

        return seller.getSellerId();
    }

    @Override
    public Optional<Seller> findById(Long sellerId) {
        return sellerMapper.findById(sellerId);
    }

    @Override
    public Boolean existsById(Long sellerId) {
        return sellerMapper.existsById(sellerId);
    }

    @Override
    public Boolean existsByUniqueFields(String username, String email, String registrationNumber) {
        return sellerMapper.existsByUniqueFields(username, email, registrationNumber);
    }
}

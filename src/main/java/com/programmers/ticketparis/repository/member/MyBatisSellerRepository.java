package com.programmers.ticketparis.repository.member;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.member.Seller;
import com.programmers.ticketparis.mapper.member.SellerMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisSellerRepository implements SellerRepository {

    private final SellerMapper sellerMapper;

    @Override
    public Long save(Seller seller) {
        return sellerMapper.save(seller);
    }

    @Override
    public Optional<Seller> findById(Long id) {
        return sellerMapper.findById(id);
    }

    @Override
    public Boolean existsById(Long sellerId) {
        return sellerMapper.existsById(sellerId);
    }

    @Override
    public Boolean existsByUsernameOrEmail(String username, String email) {
        return sellerMapper.existsByUsernameOrEmail(username, email);
    }
}

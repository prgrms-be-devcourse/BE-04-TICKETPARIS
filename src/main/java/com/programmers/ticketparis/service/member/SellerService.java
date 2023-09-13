package com.programmers.ticketparis.service.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.member.Seller;
import com.programmers.ticketparis.dto.member.SellerCreateRequest;
import com.programmers.ticketparis.dto.member.SellerResponse;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.SellerException;
import com.programmers.ticketparis.repository.member.SellerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    @Transactional
    public Long createAccount(SellerCreateRequest sellerCreateRequest) {
        validateSellerNotExistsByUniqueFields(sellerCreateRequest.getUsername(),
            sellerCreateRequest.getEmail(), sellerCreateRequest.getRegistrationNumber());

        Seller seller = sellerCreateRequest.toEntity();
        return sellerRepository.save(seller);
    }

    public SellerResponse findSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId)
            .map(SellerResponse::from)
            .orElseThrow(
                () -> new SellerException(ExceptionRule.NOT_EXIST_SELLER, List.of(String.valueOf(sellerId))));
    }

    private void validateSellerNotExistsByUniqueFields(String username, String email, String registrationNumber) {
        if (sellerRepository.existsByUniqueFields(username, email, registrationNumber)) {
            throw new SellerException(ExceptionRule.SELLER_ALREADY_CREATED,
                List.of(username, email, registrationNumber));
        }
    }

    //todo: 추후 [명세서 우선순위:2] 기능인 update, delete 메서드 구현되면 해당 메서드 활용
    private void validateSellerExists(Long sellerId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new SellerException(ExceptionRule.NOT_EXIST_SELLER, List.of(String.valueOf(sellerId)));
        }
    }
}

package com.programmers.ticketparis.member.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.member.domain.Seller;
import com.programmers.ticketparis.member.dto.request.SellerCreateRequest;
import com.programmers.ticketparis.member.dto.response.SellerIdResponse;
import com.programmers.ticketparis.member.dto.response.SellerResponse;
import com.programmers.ticketparis.member.exception.SellerException;
import com.programmers.ticketparis.member.repository.SellerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellerService {

    private final SellerRepository sellerRepository;

    @Transactional
    public SellerIdResponse createSeller(SellerCreateRequest sellerCreateRequest) {
        validateSellerNotExistsByUniqueFields(
            sellerCreateRequest.getUsername(),
            sellerCreateRequest.getEmail(),
            sellerCreateRequest.getRegistrationNumber()
        );

        Seller seller = sellerCreateRequest.toEntity();
        Long sellerId = sellerRepository.save(seller);

        return SellerIdResponse.from(sellerId);
    }

    public SellerResponse findSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId)
            .map(SellerResponse::from)
            .orElseThrow(() -> new SellerException(SELLER_NOT_EXIST, sellerId));
    }

    private void validateSellerNotExistsByUniqueFields(String username, String email, String registrationNumber) {
        if (sellerRepository.existsByUniqueFields(username, email, registrationNumber)) {
            throw new SellerException(SELLER_ALREADY_EXIST, username, email, registrationNumber);
        }
    }

    //todo: 추후 [명세서 우선순위:2] 기능인 update, delete 메서드 구현되면 해당 메서드 활용
    private void validateSellerExists(Long sellerId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new SellerException(SELLER_NOT_EXIST, sellerId);
        }
    }
}

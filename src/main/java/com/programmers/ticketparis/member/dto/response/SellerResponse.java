package com.programmers.ticketparis.member.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.member.domain.Seller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellerResponse {

    private Long sellerId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private String registrationNumber;
    private String storeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

    private SellerResponse(Seller seller) {
        this.sellerId = seller.getSellerId();
        this.username = seller.getUsername();
        this.name = seller.getName();
        this.email = seller.getEmail();
        this.phone = seller.getPhone();
        this.registrationNumber = seller.getRegistrationNumber();
        this.storeName = seller.getStoreName();
        this.createdDatetime = seller.getCreatedDatetime();
        this.updatedDatetime = seller.getUpdatedDatetime();
    }

    public static SellerResponse from(Seller seller) {
        return new SellerResponse(seller);
    }
}

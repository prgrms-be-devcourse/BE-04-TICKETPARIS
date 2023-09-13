package com.programmers.ticketparis.dto.member;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.domain.member.Seller;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerResponse {

    private Long sellerId;

    private String username;

    private String name;

    private String email;

    private String phone;

    private String registrationNumber;

    private String storeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
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

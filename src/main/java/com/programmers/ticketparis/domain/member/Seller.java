package com.programmers.ticketparis.domain.member;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {

    private Long sellerId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String registrationNumber;

    @NotNull
    private String storeName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @Builder
    private Seller(String username, String password, String name, String registrationNumber, String storeName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.storeName = storeName;
        this.email = email;
        this.phone = phone;
    }
}

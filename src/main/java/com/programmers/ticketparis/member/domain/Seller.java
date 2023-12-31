package com.programmers.ticketparis.member.domain;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.exception.SellerException;

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
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String registrationNumber;

    @NotNull
    private String storeName;

    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @Builder
    private Seller(
        String username,
        String password,
        String name,
        String email,
        String phone,
        String registrationNumber,
        String storeName
    ) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registrationNumber = registrationNumber;
        this.storeName = storeName;
    }

    public Boolean checkPassword(String plainPassword) {
        try {
            return BCrypt.checkpw(plainPassword, this.password);
        } catch (Exception e) {
            throw new SellerException(ExceptionRule.LOGIN_FAILED_PASSWORD_INVALID, e, plainPassword);
        }
    }
}

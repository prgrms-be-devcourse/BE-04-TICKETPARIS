package com.programmers.ticketparis.domain.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    private Long customerId;

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
    private LocalDate birthDate;

    @NotNull
    private String address;

    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @Builder
    private Customer(String username, String password, String name, String email, String phone, LocalDate birthDate,
        String address
    ) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }
}

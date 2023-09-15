package com.programmers.ticketparis.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.member.SellerCreateRequest;
import com.programmers.ticketparis.dto.member.SellerIdResponse;
import com.programmers.ticketparis.dto.member.SellerResponse;
import com.programmers.ticketparis.service.member.SellerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SellerIdResponse createAccount(@Valid @RequestBody SellerCreateRequest sellerCreateRequest) {
        return sellerService.createAccount(sellerCreateRequest);
    }

    @GetMapping("/{sellerId}")
    public SellerResponse findSellerById(@PathVariable Long sellerId) {
        return sellerService.findSellerById(sellerId);
    }
}

package com.programmers.ticketparis.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.ApiResponse;
import com.programmers.ticketparis.dto.member.SellerCreateRequest;
import com.programmers.ticketparis.dto.member.SellerResponse;
import com.programmers.ticketparis.service.member.SellerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createAccount(@Valid @RequestBody SellerCreateRequest sellerCreateRequest,
        HttpServletRequest httpServletRequest
    ) {
        Long sellerId = sellerService.createAccount(sellerCreateRequest);

        return ApiResponse.of(httpServletRequest.getRequestURI(), sellerId);
    }

    @GetMapping("/{sellerId}")
    public ApiResponse<SellerResponse> findSellerById(@PathVariable("sellerId") Long sellerId,
        HttpServletRequest httpServletRequest
    ) {
        return ApiResponse.of(httpServletRequest.getRequestURI(), sellerService.findSellerById(sellerId));
    }
}

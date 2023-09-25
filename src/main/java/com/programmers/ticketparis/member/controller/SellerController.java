package com.programmers.ticketparis.member.controller;

import com.programmers.ticketparis.common.dto.ApiResponseType;
import com.programmers.ticketparis.member.dto.request.SellerCreateRequest;
import com.programmers.ticketparis.member.dto.response.SellerIdResponse;
import com.programmers.ticketparis.member.dto.response.SellerResponse;
import com.programmers.ticketparis.member.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Seller API")
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "판매자 생성",
        description = "판매자 생성 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "판매자 생성 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "잘못된 판매자 생성 요청", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public SellerIdResponse createSeller(@Valid @RequestBody SellerCreateRequest sellerCreateRequest) {
        return sellerService.createSeller(sellerCreateRequest);
    }

    @GetMapping("/{sellerId}")
    @Operation(
        summary = "판매자 조회",
        description = "판매자 ID를 이용한 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "판매자 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 판매자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public SellerResponse findSellerById(@PathVariable Long sellerId) {
        return sellerService.findSellerById(sellerId);
    }
}

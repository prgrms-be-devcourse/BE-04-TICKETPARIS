package com.programmers.ticketparis.member.controller;

import com.programmers.ticketparis.common.dto.ApiResponseType;
import com.programmers.ticketparis.member.dto.request.CustomerCreateRequest;
import com.programmers.ticketparis.member.dto.response.CustomerIdResponse;
import com.programmers.ticketparis.member.dto.response.CustomerResponse;
import com.programmers.ticketparis.member.service.CustomerService;
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
@Tag(name = "Customer API")
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "고객 생성",
        description = "고객 생성 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "고객 생성 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "잘못된 고객 생성 요청", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public CustomerIdResponse createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.createCustomer(customerCreateRequest);
    }

    @GetMapping("/{customerId}")
    @Operation(
        summary = "고객 조회",
        description = "고객 ID를 이용한 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "고객 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 고객을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public CustomerResponse findCustomerById(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId);
    }
}

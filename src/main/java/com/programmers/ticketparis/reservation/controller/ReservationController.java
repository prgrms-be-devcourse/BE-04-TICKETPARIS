package com.programmers.ticketparis.reservation.controller;

import com.programmers.ticketparis.common.dto.ApiResponseType;
import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.reservation.service.ReservationRedissonFacade;
import com.programmers.ticketparis.reservation.service.ReservationService;
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

import java.util.List;

@RestController
@Tag(name = "Reservation API")
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRedissonFacade reservationRedissonFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "예매 생성",
        description = "예매 생성 API")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "예매 생성 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "잘못된 예매 생성 요청", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public ReservationIdResponse createReservation(
        @Valid @RequestBody ReservationCreateRequest reservationCreateRequest) {
        return reservationRedissonFacade.createReservation(reservationCreateRequest);
    }

    @PatchMapping("/{reservationId}")
    @Operation(
        summary = "예매 취소",
        description = "예매 취소 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "예매 취소 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 예매를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "500", description = "올바르지 않은 예매 상태", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true)
    })
    public ReservationIdResponse cancelReservationById(@PathVariable Long reservationId) {
        return reservationService.cancelReservationById(reservationId);
    }

    @GetMapping("/{reservationId}")
    @Operation(
        summary = "예매 조회",
        description = "특정 예매를 조회하는 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "예매 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 예매를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public ReservationResponse findReservationById(@PathVariable Long reservationId) {
        return reservationService.findReservationById(reservationId);
    }

    @GetMapping
    @Operation(
        summary = "페이지별 예매 조회",
        description = "페이지 번호와 크기에 따라 예매 목록 조회 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "예매 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 예매를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public List<ReservationResponse> findReservationsByPage(@RequestParam Integer pageNum, @RequestParam Integer size) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return reservationService.findReservationsByPage(pageable);
    }
}

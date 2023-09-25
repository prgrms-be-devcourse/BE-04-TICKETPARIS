package com.programmers.ticketparis.performance.controller;

import com.programmers.ticketparis.common.dto.ApiResponseType;
import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.performance.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.performance.dto.response.PerformanceIdResponse;
import com.programmers.ticketparis.performance.dto.response.PerformanceResponse;
import com.programmers.ticketparis.performance.service.PerformanceService;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
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
@RequiredArgsConstructor
@Tag(name = "Performance API")
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "공연 생성",
        description = "공연 생성 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "공연 생성 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "잘못된 공연 생성 요청", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public PerformanceIdResponse createPerformance(
        @Valid @RequestBody PerformanceCreateRequest performanceCreateRequest
    ) {
        return performanceService.createPerformance(performanceCreateRequest);
    }

    @GetMapping(value = "/{performanceId}")
    @Operation(
        summary = "공연 조회",
        description = "공연 ID를 이용한 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공연 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public PerformanceResponse findPerformanceById(@PathVariable Long performanceId) {
        return performanceService.findPerformanceById(performanceId);
    }

    @GetMapping
    @Operation(
        summary = "페이지별 공연 조회",
        description = "페이지 번호와 크기에 따른 공연 목록 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공연 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public List<PerformanceResponse> findPerformancesByPage(@RequestParam Integer pageNum, @RequestParam Integer size) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return performanceService.findPerformancesByPage(pageable);
    }

    @GetMapping("/{performanceId}/reservations")
    @Operation(
        summary = "공연 별 예매 내역 조회",
        description = "공연 별 예매 내역 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공연 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public List<ReservationResponse> findReservationsByPerformanceIdWithPage(
        @PathVariable Long performanceId,
        @RequestParam Integer pageNum,
        @RequestParam Integer size
    ) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return performanceService.findReservationsByPerformanceIdWithPage(performanceId, pageable);
    }

    @PatchMapping("/{performanceId}")
    @Operation(
        summary = "공연 수정",
        description = "페이지 번호와 크기에 따라 스케줄 목록 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공연 수정 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public PerformanceIdResponse updatePerformance(
        @PathVariable Long performanceId,
        @Valid @RequestBody PerformanceUpdateRequest performanceUpdateRequest
    ) {
        return performanceService.updatePerformance(performanceId, performanceUpdateRequest);
    }

    @DeleteMapping("/{performanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "공연 삭제",
        description = "특정 공연 삭제 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공연 삭제 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
    })
    public void deletePerformanceById(@PathVariable Long performanceId) {
        performanceService.deletePerformanceById(performanceId);
    }
}

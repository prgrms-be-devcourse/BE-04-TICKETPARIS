package com.programmers.ticketparis.performance.controller;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.performance.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.performance.dto.response.PerformanceIdResponse;
import com.programmers.ticketparis.performance.dto.response.PerformanceResponse;
import com.programmers.ticketparis.performance.service.PerformanceService;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "공연 생성 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 공연 생성 요청")})
    public PerformanceIdResponse createPerformance(
        @Valid @RequestBody PerformanceCreateRequest performanceCreateRequest
    ) {
        return performanceService.createPerformance(performanceCreateRequest);
    }

    @GetMapping("/{performanceId}")
    @Operation(
        summary = "공연 조회",
        description = "공연 ID를 이용한 조회 API") //
    @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음")//
    public PerformanceResponse findPerformanceById(@PathVariable Long performanceId) {
        return performanceService.findPerformanceById(performanceId);
    }

    @GetMapping
    @Operation(
        summary = "페이지별 공연 조회",
        description = "페이지 번호와 크기에 따라 공연 목록 조회 API")
    @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음")
    public List<PerformanceResponse> findPerformancesByPage(@RequestParam Integer pageNum, @RequestParam Integer size) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return performanceService.findPerformancesByPage(pageable);
    }

    @GetMapping("/{performanceId}/reservations")
    @Operation(
        summary = "페이지별 공연 조회",
        description = "페이지 번호와 크기 및 공연 ID를 통한 조회 API")
    @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음")
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
        summary = "스케줄별 페이지 조회",
        description = "페이지 번호와 크기에 따라 스케줄 목록 조회 API")
    @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음")
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
    @ApiResponse(responseCode = "404", description = "해당 공연을 찾을 수 없음")
    public void deletePerformanceById(@PathVariable Long performanceId) {
        performanceService.deletePerformanceById(performanceId);
    }
}

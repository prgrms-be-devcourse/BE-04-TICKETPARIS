package com.programmers.ticketparis.schedule.controller;

import com.programmers.ticketparis.common.dto.ApiResponseType;
import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.schedule.dto.request.ScheduleCreateRequest;
import com.programmers.ticketparis.schedule.dto.response.ScheduleIdResponse;
import com.programmers.ticketparis.schedule.dto.response.ScheduleResponse;
import com.programmers.ticketparis.schedule.service.ScheduleService;
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
@Tag(name = "Schedule API")
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "스케줄 생성",
        description = "스케줄 생성 API")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "스케줄 생성 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "잘못된 스케줄 생성 요청", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true)
    })
    public ScheduleIdResponse createSchedule(@Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        return scheduleService.createSchedule(scheduleCreateRequest);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(
        summary = "스케줄 삭제",
        description = "특정 스케줄 삭제 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "스케줄 삭제 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 스케줄을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduleById(@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
    }

    @GetMapping
    @Operation(
        summary = "스케줄별 페이지 조회",
        description = "페이지 번호와 크기에 따라 스케줄 목록 조회 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "스케줄 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 스케줄을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true)
    })
    public List<ScheduleResponse> findSchedulesByPage(@RequestParam Integer pageNum, @RequestParam Integer size) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return scheduleService.findSchedulesByPage(pageable);
    }

    @GetMapping("/{scheduleId}/reservations")
    @Operation(
        summary = "스케줄별 예매 내역 페이지 조회 ",
        description = "페이지 번호와 크기에 따른 스케줄별 예매 내역 목록 조회 API")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "스케줄 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "404", description = "해당 스케줄을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true),
        @ApiResponse(responseCode = "500", description = "해당 예매를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true)
    })
    public List<ReservationResponse> findReservationsByScheduleIdWithPage(
        @RequestParam Integer pageNum,
        @RequestParam Integer size,
        @PathVariable Integer scheduleId
    ) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return scheduleService.findReservationsByScheduleIdWithPage(scheduleId, pageable);
    }
}

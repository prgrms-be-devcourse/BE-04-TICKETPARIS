package com.programmers.ticketparis.schedule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.schedule.dto.request.ScheduleCreateRequest;
import com.programmers.ticketparis.schedule.dto.response.ScheduleIdResponse;
import com.programmers.ticketparis.schedule.dto.response.ScheduleResponse;
import com.programmers.ticketparis.schedule.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleIdResponse createSchedule(@Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        return scheduleService.createSchedule(scheduleCreateRequest);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduleById(@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
    }

    @GetMapping
    public List<ScheduleResponse> findSchedulesByPage(@RequestParam Integer pageNum, @RequestParam Integer size) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        return scheduleService.findSchedulesByPage(pageable);
    }

    @GetMapping("/{scheduleId}/reservations")
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

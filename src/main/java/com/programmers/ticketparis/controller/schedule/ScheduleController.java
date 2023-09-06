package com.programmers.ticketparis.controller.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.ApiResponse;
import com.programmers.ticketparis.dto.schedule.request.ScheduleCreateRequest;
import com.programmers.ticketparis.dto.schedule.response.ScheduleResponse;
import com.programmers.ticketparis.service.schedule.ScheduleService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performances/{performanceId}")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ScheduleResponse> createSchedule(@PathVariable Long performanceId, @RequestBody ScheduleCreateRequest request, HttpServletRequest httpServletRequest) {
        ScheduleResponse response = scheduleService.createSchedule(performanceId, request);
        String requestURI = httpServletRequest.getRequestURI();

        return ApiResponse.of(requestURI, response);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduleById(@PathVariable Long performanceId, @PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(performanceId, scheduleId);
    }
}

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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ScheduleResponse> createSchedule(
        @Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest,
        HttpServletRequest httpServletRequest
    ) {
        ScheduleResponse scheduleResponse = scheduleService.createSchedule(scheduleCreateRequest);

        return ApiResponse.of(httpServletRequest.getRequestURI(), scheduleResponse);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduleById(@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
    }
}

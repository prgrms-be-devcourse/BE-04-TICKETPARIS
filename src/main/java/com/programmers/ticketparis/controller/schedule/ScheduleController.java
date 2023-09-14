package com.programmers.ticketparis.controller.schedule;

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

import com.programmers.ticketparis.domain.pageable.Pageable;
import com.programmers.ticketparis.dto.schedule.request.ScheduleCreateRequest;
import com.programmers.ticketparis.dto.schedule.response.ScheduleIdResponse;
import com.programmers.ticketparis.dto.schedule.response.ScheduleResponse;
import com.programmers.ticketparis.service.schedule.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleIdResponse createSchedule(@Valid @RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduleById(@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
    }

    @GetMapping
    public List<ScheduleResponse> findSchedulesByPage(
        @RequestParam Integer pageNum,
        @RequestParam Integer size
    ) {
        Pageable pageable = Pageable.builder()
            .pageNum(pageNum)
            .size(size)
            .build();

        List<ScheduleResponse> scheduleResponses = scheduleService.findSchedulesByPage(pageable);

        return scheduleResponses;
    }
}

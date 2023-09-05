package com.programmers.ticketparis.service.schedule;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.schedule.Schedule;
import com.programmers.ticketparis.dto.schedule.request.ScheduleCreateRequest;
import com.programmers.ticketparis.dto.schedule.response.ScheduleResponse;
import com.programmers.ticketparis.repository.schedule.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse createSchedule(Long performanceId, ScheduleCreateRequest request) {
        Integer seatsCount = scheduleRepository.findHallSeatsCountByPerformanceId(performanceId);
        Schedule schedule = request.toEntity(seatsCount, performanceId);
        Long savedScheduleId = scheduleRepository.save(schedule);

        return ScheduleResponse.from(savedScheduleId);
    }
}

package com.programmers.ticketparis.repository.schedule;

import java.util.Optional;

import com.programmers.ticketparis.domain.schedule.Schedule;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Optional<Schedule> findById(Long performanceId, Long scheduleId);

    Boolean existsById(Long performanceId, Long scheduleId);

    Integer deleteById(Long performanceId, Long scheduleId);
}
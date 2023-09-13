package com.programmers.ticketparis.repository.schedule;

import java.util.Optional;

import com.programmers.ticketparis.domain.schedule.Schedule;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Optional<Schedule> findById(Long scheduleId);

    Boolean existsById(Long scheduleId);

    Integer deleteById(Long scheduleId);
}

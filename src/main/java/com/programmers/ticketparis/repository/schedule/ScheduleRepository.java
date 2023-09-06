package com.programmers.ticketparis.repository.schedule;

import com.programmers.ticketparis.domain.schedule.Schedule;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Boolean isExistedScheduleId(Long scheduleId);

    void deleteById(Long scheduleId);
}

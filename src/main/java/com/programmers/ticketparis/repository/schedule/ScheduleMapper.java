package com.programmers.ticketparis.repository.schedule;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.schedule.Schedule;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Optional<Schedule> findById(Long performanceId, Long scheduleId);

    Integer deleteById(Long performanceId, Long scheduleId);
}

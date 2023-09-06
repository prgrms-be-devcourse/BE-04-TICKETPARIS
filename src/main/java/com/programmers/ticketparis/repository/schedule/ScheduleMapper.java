package com.programmers.ticketparis.repository.schedule;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.schedule.Schedule;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Integer deleteById(Long performanceId, Long scheduleId);
}

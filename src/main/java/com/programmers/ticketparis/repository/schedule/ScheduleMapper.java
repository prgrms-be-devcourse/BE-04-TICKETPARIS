package com.programmers.ticketparis.repository.schedule;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.schedule.Schedule;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Boolean isExistedScheduleId(Long scheduleId);

    void deleteById(Long scheduleId);
}

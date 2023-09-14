package com.programmers.ticketparis.repository.schedule;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.pageable.Pageable;
import com.programmers.ticketparis.domain.schedule.Schedule;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Optional<Schedule> findById(Long scheduleId);

    Boolean existsById(Long scheduleId);

    Integer deleteById(Long scheduleId);

    void updateSeatsCountById(Long scheduleId, Integer seatsCount);

    List<Schedule> findSchedulesByPage(Pageable pageable);
}

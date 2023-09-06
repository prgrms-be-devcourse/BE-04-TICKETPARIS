package com.programmers.ticketparis.repository.schedule;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.schedule.Schedule;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisScheduleRepository implements ScheduleRepository {

    private final ScheduleMapper scheduleMapper;

    @Override
    public Long save(Schedule schedule) {
        scheduleMapper.save(schedule);

        return schedule.getScheduleId();
    }

    @Override
    public Integer findHallSeatsCountByPerformanceId(Long performanceId) {
        return scheduleMapper.findHallSeatsCountByPerformanceId(performanceId);
    }

    @Override
    public Integer deleteById(Long performanceId, Long scheduleId) {
        return scheduleMapper.deleteById(performanceId, scheduleId);
    }
}

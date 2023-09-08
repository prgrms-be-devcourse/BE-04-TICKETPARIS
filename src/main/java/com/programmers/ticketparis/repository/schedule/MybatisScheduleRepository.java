package com.programmers.ticketparis.repository.schedule;

import java.util.Optional;

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
    public Optional<Schedule> findById(Long scheduleId) {
        return scheduleMapper.findById(scheduleId);
    }

    @Override
    public Boolean existsById(Long scheduleId) {
        return scheduleMapper.existsById(scheduleId);
    }

    @Override
    public Integer deleteById(Long scheduleId) {
        return scheduleMapper.deleteById(scheduleId);
    }

    @Override
    public void updateSeatsCountById(Long scheduleId, Integer seatsCount) {
        scheduleMapper.updateSeatsCountById(scheduleId, seatsCount);
    }
}

package com.programmers.ticketparis.schedule.repository.mybatis;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.schedule.domain.Schedule;
import com.programmers.ticketparis.schedule.repository.ScheduleRepository;
import com.programmers.ticketparis.schedule.repository.mapper.ScheduleMapper;

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

    @Override
    public List<Schedule> findSchedulesByPage(Pageable pageable) {
        return scheduleMapper.findSchedulesByPage(pageable);
    }

    @Override
    public List<Reservation> findReservationsByScheduleIdWithPage(Integer scheduleId, Pageable pageable) {
        return scheduleMapper.findReservationsByScheduleIdWithPage(scheduleId, pageable);
    }
}

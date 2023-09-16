package com.programmers.ticketparis.schedule.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.schedule.domain.Schedule;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Optional<Schedule> findById(Long scheduleId);

    Boolean existsById(Long scheduleId);

    Integer deleteById(Long scheduleId);

    void updateSeatsCountById(Long scheduleId, Integer seatsCount);

    List<Schedule> findSchedulesByPage(Pageable pageable);

    List<Reservation> findReservationsByScheduleIdWithPage(Integer scheduleId, Pageable pageable);
}

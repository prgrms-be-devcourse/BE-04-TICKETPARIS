package com.programmers.ticketparis.repository.schedule;

import java.util.List;
import java.util.Optional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.schedule.Schedule;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    Integer findHallSeatsCountByPerformanceId(Long performanceId);

    Optional<Schedule> findById(Long scheduleId);

    Boolean existsById(Long scheduleId);

    Integer deleteById(Long scheduleId);

    void updateSeatsCountById(Long scheduleId, Integer seatsCount);

    List<Schedule> findSchedulesByPage(Pageable pageable);

    List<Reservation> findReservationsByScheduleIdWithPage(Integer scheduleId, Pageable pageable);
}

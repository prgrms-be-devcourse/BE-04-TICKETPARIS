package com.programmers.ticketparis.schedule.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.schedule.domain.Schedule;
import com.programmers.ticketparis.schedule.dto.request.ScheduleCreateRequest;
import com.programmers.ticketparis.schedule.dto.response.ScheduleIdResponse;
import com.programmers.ticketparis.schedule.dto.response.ScheduleResponse;
import com.programmers.ticketparis.schedule.exception.ScheduleException;
import com.programmers.ticketparis.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleIdResponse createSchedule(ScheduleCreateRequest scheduleCreateRequest) {
        // TODO: performanceId에 해당하는 공연 데이터가 없는 경우 예외 처리 예정 (2023.09.06 김영주 작성)
        // TODO: startDatetime이 공연의 시작, 종료날짜 범위를 벗어나는 경우 예외 처리 예정 (2023.09.06 김영주 작성)

        Integer seatsCount = findHallSeatsCountByPerformanceId(scheduleCreateRequest.getPerformanceId());
        Schedule schedule = scheduleCreateRequest.toEntity(seatsCount);
        Long savedScheduleId = scheduleRepository.save(schedule);

        return ScheduleIdResponse.from(savedScheduleId);
    }

    public Schedule findByScheduleId(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new ScheduleException(SCHEDULE_NOT_EXIST, scheduleId));
    }

    public Schedule findByIdWithPessimisticLock(Long scheduleId) {
        return scheduleRepository.findByIdWithPessimisticLock(scheduleId)
            .orElseThrow(() -> new ScheduleException(SCHEDULE_NOT_EXIST, scheduleId));
    }

    @Transactional
    public void deleteScheduleById(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ScheduleException(SCHEDULE_NOT_EXIST, scheduleId);
        }

        scheduleRepository.deleteById(scheduleId);
    }

    @Transactional
    public void updateSeatsCountById(Long scheduleId, Integer seatsCount) {
        scheduleRepository.updateSeatsCountById(scheduleId, seatsCount);
    }

    public Integer findHallSeatsCountByPerformanceId(Long performanceId) {
        return scheduleRepository.findHallSeatsCountByPerformanceId(performanceId);
    }

    public List<ScheduleResponse> findSchedulesByPage(Pageable pageable) {
        List<Schedule> schedules = scheduleRepository.findSchedulesByPage(pageable);

        return schedules.stream()
            .map(ScheduleResponse::from)
            .toList();
    }

    public List<ReservationResponse> findReservationsByScheduleIdWithPage(Integer scheduleId, Pageable pageable) {
        List<Reservation> reservations = scheduleRepository.findReservationsByScheduleIdWithPage(scheduleId, pageable);

        return reservations.stream()
            .map(ReservationResponse::from)
            .toList();
    }
}

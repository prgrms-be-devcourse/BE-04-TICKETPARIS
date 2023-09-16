package com.programmers.ticketparis.reservation.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;
import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.reservation.exception.ReservationException;
import com.programmers.ticketparis.reservation.repository.ReservationRepository;
import com.programmers.ticketparis.schedule.domain.Schedule;
import com.programmers.ticketparis.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ScheduleService scheduleService;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationIdResponse createReservation(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = reservationCreateRequest.toEntity();

        Schedule schedule = scheduleService.findByScheduleId(reservation.getScheduleId());
        schedule.decreaseSeatsCount();
        scheduleService.updateSeatsCountById(schedule.getScheduleId(), schedule.getSeatsCount());

        Long reservationId = reservationRepository.save(reservation);

        return ReservationIdResponse.from(reservationId);
    }

    @Transactional
    public ReservationIdResponse cancelReservationById(Long reservationId) {
        validateReservationExists(reservationId);
        Reservation reservation = getReservationById(reservationId);

        Schedule schedule = scheduleService.findByScheduleId(reservation.getScheduleId());
        Integer totalSeatsCount = scheduleService.findHallSeatsCountByPerformanceId(schedule.getPerformanceId());
        schedule.increaseSeatsCount(totalSeatsCount);
        scheduleService.updateSeatsCountById(schedule.getScheduleId(), schedule.getSeatsCount());

        Long canceledReservationId = reservationRepository.updateReservationStatusById(reservationId,
            ReservationStatus.CANCELED);

        return ReservationIdResponse.from(canceledReservationId);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);

        return ReservationResponse.from(reservation);
    }

    private Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ReservationException(RESERVATION_NOT_EXIST, reservationId));
    }

    public List<ReservationResponse> findReservationsByPage(Pageable pageable) {
        List<Reservation> reservations = reservationRepository.findReservationsByPage(pageable);

        return reservations.stream()
            .map(ReservationResponse::from)
            .toList();
    }

    private void validateReservationExists(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationException(RESERVATION_NOT_EXIST, reservationId);
        }
    }
}

package com.programmers.ticketparis.service.reservation;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.domain.schedule.Schedule;
import com.programmers.ticketparis.dto.reservation.request.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.response.ReservationResponse;
import com.programmers.ticketparis.exception.ReservationException;
import com.programmers.ticketparis.repository.reservation.ReservationRepository;
import com.programmers.ticketparis.service.schedule.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScheduleService scheduleService;

    @Transactional
    public Long createReservation(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = reservationCreateRequest.toEntity();
        Schedule schedule = scheduleService.findByScheduleId(reservation.getScheduleId());
        schedule.decreaseSeatsCount();
        scheduleService.updateSeatsCountById(schedule.getScheduleId(), schedule.getSeatsCount());

        return reservationRepository.save(reservation);
    }

    @Transactional
    public Long cancelReservationById(Long reservationId) {
        validateReservationExists(reservationId);
        Reservation reservation = getReservationById(reservationId);
        Schedule schedule = scheduleService.findByScheduleId(reservation.getScheduleId());
        scheduleService.updateSeatsCountById(schedule.getScheduleId(), schedule.getSeatsCount());

        return reservationRepository.updateReservationStatusById(reservationId, ReservationStatus.CANCELED);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .map(ReservationResponse::from)
            .orElseThrow(() -> new ReservationException(NOT_EXIST_RESERVATION,
                List.of(String.valueOf(reservationId))));
    }

    private Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ReservationException(RESERVATION_NOT_EXIST,
                List.of(String.valueOf(reservationId))));
    }

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationResponse::from).toList();
    }

    private void validateReservationExists(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationException(NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId)));
        }
    }
}

package com.programmers.ticketparis.service.reservation;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.dto.reservation.request.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.response.ReservationIdResponse;
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
    public ReservationIdResponse createReservation(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = reservationCreateRequest.toEntity();
        Long reservationId = reservationRepository.save(reservation);

        return ReservationIdResponse.from(reservationId);
    }

    @Transactional
    public ReservationIdResponse cancelReservationById(Long reservationId) {
        validateReservationExists(reservationId);
        Long canceledReservationId = reservationRepository.updateReservationStatusById(
            reservationId, ReservationStatus.CANCELED);

        return ReservationIdResponse.from(canceledReservationId);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .map(ReservationResponse::from)
            .orElseThrow(() -> new ReservationException(RESERVATION_NOT_EXIST, reservationId));
    }

    public List<ReservationResponse> findReservationsByPage(Pageable pageable) {
        List<Reservation> reservations = reservationRepository.findReservationsByPage(pageable);

        return reservations.stream()
            .map(ReservationResponse::from).toList();
    }

    private void validateReservationExists(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationException(RESERVATION_NOT_EXIST, reservationId);
        }
    }
}

package com.programmers.ticketparis.service;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.dto.reservation.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.ReservationResponse;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.ReservationException;
import com.programmers.ticketparis.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public void createReservation(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = reservationCreateRequest.toEntity();
        reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        existsReservationById(reservationId);
        reservationRepository.update(reservationId, ReservationStatus.CANCELED);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId))));

        return ReservationResponse.of(reservation);
    }

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationResponse::of).toList();
    }

    private void existsReservationById(Long reservationId) {
        if (!reservationRepository.existById(reservationId)) {
            throw new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId)));
        }
    }
}

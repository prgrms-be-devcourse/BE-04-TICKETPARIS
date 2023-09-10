package com.programmers.ticketparis.service.reservation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.dto.reservation.request.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.response.ReservationResponse;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.ReservationException;
import com.programmers.ticketparis.repository.reservation.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Long createReservation(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = reservationCreateRequest.toEntity();

        return reservationRepository.save(reservation);
    }

    @Transactional
    public Long cancelReservationById(Long reservationId) {
        validateReservationExists(reservationId);

        return reservationRepository.updateReservationStatusById(reservationId, ReservationStatus.CANCELED);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION,
                List.of(String.valueOf(reservationId))));

        return ReservationResponse.builder()
            .reservationId(reservation.getReservationId())
            .reservationStatus(reservation.getReservationStatus())
            .createdDatetime(reservation.getCreatedDatetime())
            .updatedDatetime(reservation.getUpdatedDatetime())
            .build();
    }

    public List<ReservationResponse> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(reservation -> ReservationResponse.builder()
            .reservationId(reservation.getReservationId())
            .reservationStatus(reservation.getReservationStatus())
            .createdDatetime(reservation.getCreatedDatetime())
            .updatedDatetime(reservation.getUpdatedDatetime())
            .build()
        ).toList();
    }

    private void validateReservationExists(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId)));
        }
    }
}

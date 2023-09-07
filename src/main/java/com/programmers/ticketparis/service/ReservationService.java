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
    public void cancelReservationById(Long reservationId) {
        existsReservationById(reservationId);
        reservationRepository.updateById(reservationId, ReservationStatus.CANCELED);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId))));

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

    private void existsReservationById(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId)));
        }
    }
}

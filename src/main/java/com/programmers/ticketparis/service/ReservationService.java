package com.programmers.ticketparis.service;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.dto.reservation.ReservationCreateRequest;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.ReservationException;
import com.programmers.ticketparis.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public void create(ReservationCreateRequest reservationCreateRequest) {
        Reservation reservation = reservationCreateRequest.toEntity();
        reservationRepository.create(reservation);
    }

    @Transactional
    public void cancel(Long reservationId) {
        existById(reservationId);

        reservationRepository.cancel(reservationId, ReservationStatus.CANCELED);
    }

    private void existById(Long reservationId) {
        if (!reservationRepository.existById(reservationId)) {
            throw new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId)));
        }
    }
}

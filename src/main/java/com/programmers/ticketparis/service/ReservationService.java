package com.programmers.ticketparis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.dto.reservation.ReservationCancelRequest;
import com.programmers.ticketparis.dto.reservation.ReservationCreateRequest;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.ReservationException;
import com.programmers.ticketparis.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

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
    public void cancel(Long reservationId, ReservationCancelRequest reservationCancelRequest) {
        existById(reservationId);

        Reservation reservation = reservationCancelRequest.toEntity();
        reservationRepository.cancel(reservationId, reservation);
    }

    private void existById(Long reservationId) {
        if (!reservationRepository.existById(reservationId)) {
            throw new ReservationException(ExceptionRule.NOT_EXIST_RESERVATION, List.of(String.valueOf(reservationId)));
        }
    }
}

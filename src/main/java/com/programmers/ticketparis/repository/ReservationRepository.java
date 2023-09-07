package com.programmers.ticketparis.repository;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void create(Reservation reservation);

    void cancel(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findAll();

    boolean existById(Long reservationId);
}

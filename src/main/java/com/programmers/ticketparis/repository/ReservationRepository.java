package com.programmers.ticketparis.repository;

import com.programmers.ticketparis.domain.reservation.Reservation;

public interface ReservationRepository {

    void create(Reservation reservation);

    void cancel(Long reservationId, Reservation reservation);

    boolean existById(Long reservationId);
}

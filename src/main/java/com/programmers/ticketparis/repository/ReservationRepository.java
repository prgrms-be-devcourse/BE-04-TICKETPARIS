package com.programmers.ticketparis.repository;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

public interface ReservationRepository {

    void create(Reservation reservation);

    void cancel(Long reservationId, ReservationStatus reservationStatus);

    boolean existById(Long reservationId);
}

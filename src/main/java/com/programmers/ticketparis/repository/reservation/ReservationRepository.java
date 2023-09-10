package com.programmers.ticketparis.repository.reservation;

import java.util.List;
import java.util.Optional;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

public interface ReservationRepository {

    Long save(Reservation reservation);

    Long updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findAll();

    Boolean existsById(Long reservationId);
}

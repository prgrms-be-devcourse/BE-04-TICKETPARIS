package com.programmers.ticketparis.reservation.repository;

import java.util.List;
import java.util.Optional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;

public interface ReservationRepository {

    Long save(Reservation reservation);

    Long updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findReservationsByPage(Pageable pageable);

    Boolean existsById(Long reservationId);
}

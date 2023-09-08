package com.programmers.ticketparis.repository.reservation;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void save(Reservation reservation);

    void updateById(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findAll();

    Boolean existsById(Long reservationId);

    void deleteAll();
}

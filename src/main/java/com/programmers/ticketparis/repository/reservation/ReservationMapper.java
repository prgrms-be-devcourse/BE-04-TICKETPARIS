package com.programmers.ticketparis.repository.reservation;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

@Mapper
public interface ReservationMapper {

    void save(Reservation reservation);

    void updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findAll();

    Boolean existsById(Long reservationId);

    void deleteAll();
}

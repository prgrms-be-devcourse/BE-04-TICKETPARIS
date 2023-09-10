package com.programmers.ticketparis.repository.reservation;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

@Mapper
public interface ReservationMapper {

    Long save(Reservation reservation);

    Long updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findAll();

    Boolean existsById(Long reservationId);
}

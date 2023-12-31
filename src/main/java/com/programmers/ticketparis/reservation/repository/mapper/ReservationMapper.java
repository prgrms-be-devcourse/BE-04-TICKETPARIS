package com.programmers.ticketparis.reservation.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;

@Mapper
public interface ReservationMapper {

    void save(Reservation reservation);

    void updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findReservationsByPage(Pageable pageable);

    Boolean existsById(Long reservationId);
}

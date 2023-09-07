package com.programmers.ticketparis.mapper;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper {

    void save(Reservation reservation);

    void update(@Param("reservationId") Long reservationId,
                @Param("reservationStatus") ReservationStatus reservationStatus);

    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findAll();

    boolean existById(Long reservationId);
}

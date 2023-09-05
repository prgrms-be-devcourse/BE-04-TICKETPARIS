package com.programmers.ticketparis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.programmers.ticketparis.domain.reservation.Reservation;

@Mapper
public interface ReservationMapper {

    void create(Reservation reservation);

    void cancel(@Param("reservationId") Long reservationId, @Param("reservation") Reservation reservation);

    boolean existById(Long reservationId);
}

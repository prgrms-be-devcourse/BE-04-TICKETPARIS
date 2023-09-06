package com.programmers.ticketparis.mapper;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {

    void create(Reservation reservation);

    void cancel(@Param("reservationId") Long reservationId, @Param("reservationStatus") ReservationStatus reservationStatus);

    boolean existById(Long reservationId);
}

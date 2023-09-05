package com.programmers.ticketparis.repository;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public void create(Reservation reservation) {
        reservationMapper.create(reservation);
    }

    @Override
    public void cancel(Long reservationId, Reservation reservation) {
        reservationMapper.cancel(reservationId, reservation);
    }

    @Override
    public boolean existById(Long reservationId) {
        return reservationMapper.existById(reservationId);
    }
}

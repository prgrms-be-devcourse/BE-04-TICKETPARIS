package com.programmers.ticketparis.repository;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public void create(Reservation reservation) {
        reservationMapper.create(reservation);
    }

    @Override
    public void cancel(Long reservationId, ReservationStatus reservationStatus) {
        reservationMapper.cancel(reservationId, reservationStatus);
    }

    @Override
    public boolean existById(Long reservationId) {
        return reservationMapper.existById(reservationId);
    }
}

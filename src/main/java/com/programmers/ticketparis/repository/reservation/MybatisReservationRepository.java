package com.programmers.ticketparis.repository.reservation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public void save(Reservation reservation) {
        reservationMapper.save(reservation);
    }

    @Override
    public void updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus) {
        reservationMapper.updateReservationStatusById(reservationId, reservationStatus);
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationMapper.findById(reservationId);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationMapper.findAll();
    }

    @Override
    public Boolean existsById(Long reservationId) {
        return reservationMapper.existsById(reservationId);
    }
}

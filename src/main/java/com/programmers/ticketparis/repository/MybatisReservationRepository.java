package com.programmers.ticketparis.repository;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public void save(Reservation reservation) {
        reservationMapper.save(reservation);
    }

    @Override
    public void updateById(Long reservationId, ReservationStatus reservationStatus) {
        reservationMapper.updateById(reservationId, reservationStatus);
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
    public boolean existsById(Long reservationId) {
        return reservationMapper.existsById(reservationId);
    }

    @Override
    public void deleteAll() {
        reservationMapper.deleteAll();
    }
}

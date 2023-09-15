package com.programmers.ticketparis.repository.reservation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public Long save(Reservation reservation) {
        reservationMapper.save(reservation);

        return reservation.getReservationId();
    }

    @Override
    public Long updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus) {
        return reservationMapper.updateReservationStatusById(reservationId, reservationStatus);
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationMapper.findById(reservationId);
    }

    @Override
    public List<Reservation> findReservationsByPage(Pageable pageable) {
        return reservationMapper.findReservationsByPage(pageable);
    }

    @Override
    public Boolean existsById(Long reservationId) {
        return reservationMapper.existsById(reservationId);
    }
}

package com.programmers.ticketparis.reservation.repository.mybatis;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;
import com.programmers.ticketparis.reservation.repository.ReservationRepository;
import com.programmers.ticketparis.reservation.repository.mapper.ReservationMapper;

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
        reservationMapper.updateReservationStatusById(reservationId, reservationStatus);

        return reservationId;
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

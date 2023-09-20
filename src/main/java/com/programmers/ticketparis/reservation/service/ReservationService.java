package com.programmers.ticketparis.reservation.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;
import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.reservation.exception.ReservationException;
import com.programmers.ticketparis.reservation.repository.ReservationRepository;
import com.programmers.ticketparis.schedule.domain.Schedule;
import com.programmers.ticketparis.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ScheduleService scheduleService;
    private final ReservationRepository reservationRepository;
    private final RedissonClient redissonClient;

    @Transactional
    public ReservationIdResponse createReservation(ReservationCreateRequest reservationCreateRequest) {
        log.info("트랜잭션 시작");
        Reservation reservation = reservationCreateRequest.toEntity();

        RLock lock = redissonClient.getLock(reservationCreateRequest.getScheduleId().toString());

        try {
            boolean available = lock.tryLock(15, 1, TimeUnit.SECONDS);

            if (available) {
                log.error("락 획득");
                decreaseSeatsCount(reservation);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
            log.error("락 해제");
        }

        Long reservationId = reservationRepository.save(reservation);

        log.info("트랜잭션 커밋");
        return ReservationIdResponse.from(reservationId);
    }

    @Transactional
    public ReservationIdResponse cancelReservationById(Long reservationId) {
        validateReservationExists(reservationId);
        Reservation reservation = getReservationById(reservationId);

        if (isReservationStatusCanceled(reservation)) {
            throw new ReservationException(RESERVATION_ALREADY_CANCELED, reservationId);
        }

        increaseSeatsCount(reservation);

        Long canceledReservationId = reservationRepository.updateReservationStatusById(reservationId,
            ReservationStatus.CANCELED);

        return ReservationIdResponse.from(canceledReservationId);
    }

    public ReservationResponse findReservationById(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);

        return ReservationResponse.from(reservation);
    }

    private Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ReservationException(RESERVATION_NOT_EXIST, reservationId));
    }

    public List<ReservationResponse> findReservationsByPage(Pageable pageable) {
        List<Reservation> reservations = reservationRepository.findReservationsByPage(pageable);

        return reservations.stream()
            .map(ReservationResponse::from)
            .toList();
    }

    private void validateReservationExists(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new ReservationException(RESERVATION_NOT_EXIST, reservationId);
        }
    }

    private boolean isReservationStatusCanceled(Reservation reservation) {
        return reservation.getReservationStatus() == ReservationStatus.CANCELED;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void decreaseSeatsCount(Reservation reservation) {
        Schedule schedule = scheduleService.findByScheduleId(reservation.getScheduleId());
        log.info("읽어온 좌석 수 : {}", schedule.getSeatsCount());
        schedule.decreaseSeatsCount();
        scheduleService.updateSeatsCountById(schedule.getScheduleId(), schedule.getSeatsCount());
    }

    private void increaseSeatsCount(Reservation reservation) {
        Schedule schedule = scheduleService.findByScheduleId(reservation.getScheduleId());
        Integer totalSeatsCount = scheduleService.findHallSeatsCountByPerformanceId(schedule.getPerformanceId());
        schedule.increaseSeatsCount(totalSeatsCount);
        scheduleService.updateSeatsCountById(schedule.getScheduleId(), schedule.getSeatsCount());
    }
}

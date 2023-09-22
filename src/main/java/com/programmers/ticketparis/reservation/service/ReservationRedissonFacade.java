package com.programmers.ticketparis.reservation.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.programmers.ticketparis.common.exception.CommonException;
import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationRedissonFacade {

    private final RedissonClient redissonClient;
    private final ReservationService reservationService;

    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    public ReservationIdResponse createReservation(ReservationCreateRequest reservationCreateRequest) {

        String key = REDISSON_LOCK_PREFIX + reservationCreateRequest.getScheduleId();
        RLock lock = redissonClient.getLock(key);

        boolean isLocked;
        ReservationIdResponse reservationIdResponse = null;

        try {
            isLocked = lock.tryLock(30, 1, TimeUnit.SECONDS);

            if (!isLocked) {
                throw new CommonException(COMMON_LOCK_ACQUISITION_FAILED, reservationCreateRequest.toString());
            }

            reservationIdResponse = reservationService.createReservation(reservationCreateRequest);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException e) {
                log.info("Redisson Lock Already UnLock");
            }
        }

        return reservationIdResponse;
    }
}

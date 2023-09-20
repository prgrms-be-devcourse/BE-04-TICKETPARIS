package com.programmers.ticketparis.reservation.service;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationRedissonFacade {

    private final RedissonClient redissonClient;
    private final ReservationService reservationService;

    public ReservationIdResponse createReservation(ReservationCreateRequest reservationCreateRequest) {
        RLock lock = redissonClient.getLock(reservationCreateRequest.getScheduleId().toString());

        ReservationIdResponse reservationIdResponse = null;

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (available) {
                reservationIdResponse = reservationService.createReservation(reservationCreateRequest);
                log.error("락 획득");
            } else {
                log.error("락 획득 실패");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
            log.info("락 해제");
        }

        return reservationIdResponse;
    }

}

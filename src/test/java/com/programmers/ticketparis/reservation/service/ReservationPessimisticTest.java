package com.programmers.ticketparis.reservation.service;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.schedule.domain.Schedule;
import com.programmers.ticketparis.schedule.service.ScheduleService;

@SpringBootTest
class ReservationPessimisticTest {

    private static ReservationCreateRequest reservationCreateRequest;
    private static int seatsCount;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        reservationCreateRequest = ReservationCreateRequest.builder()
            .customerId(1L)
            .scheduleId(1L)
            .build();

        seatsCount = scheduleService.findByScheduleId(reservationCreateRequest.getScheduleId())
            .getSeatsCount();
    }

    @Test
    @DisplayName("고객 100명이 동시에 예약을 한다.")
    void concurrentReservationBy100Users_Success() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reservationService.createReservation(reservationCreateRequest);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Schedule schedule = scheduleService.findByScheduleId(reservationCreateRequest.getScheduleId());

        assertThat(schedule.getSeatsCount()).isEqualTo(seatsCount - 100);
    }
}

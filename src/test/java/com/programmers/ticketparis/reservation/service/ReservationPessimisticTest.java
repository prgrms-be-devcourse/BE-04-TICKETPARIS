package com.programmers.ticketparis.reservation.service;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.reservation.dto.request.ReservationCreateRequest;
import com.programmers.ticketparis.schedule.domain.Schedule;
import com.programmers.ticketparis.schedule.service.ScheduleService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReservationPessimisticTest {

    private static ReservationCreateRequest reservationCreateRequest;
    private static int seatsCount;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ScheduleService scheduleService;

    @BeforeAll
    void setUp() {
        reservationCreateRequest = ReservationCreateRequest.builder()
            .customerId(1L)
            .scheduleId(1L)
            .build();

        seatsCount = scheduleService.findByScheduleId(reservationCreateRequest.getScheduleId()).getSeatsCount();
    }

    @Test
    @Order(1)
    @Transactional
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

    @Test
    @Order(2)
    @Transactional
    @DisplayName("고객 100명이 동시에 예약 취소한다.")
    void concurrentReservationCancelBy100Users_Success() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        /**
         * 테스트시 필요한 더미 데이터 이후의 ID 값부터 시작
         * 예약번호 19번 부터 예매를 취소
         */
        for (int i = 19; i <= threadCount + 18; i++) {
            int threadNum = i;

            executorService.submit(() -> {
                try {
                    reservationService.cancelReservationById(Long.valueOf(threadNum));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Schedule schedule = scheduleService.findByScheduleId(reservationCreateRequest.getScheduleId());

        assertThat(schedule.getSeatsCount()).isEqualTo(seatsCount);
    }

}

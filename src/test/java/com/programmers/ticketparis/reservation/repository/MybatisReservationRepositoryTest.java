package com.programmers.ticketparis.reservation.repository;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;
import com.programmers.ticketparis.reservation.exception.ReservationException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MybatisReservationRepositoryTest {

    private static Reservation reservation1;
    private static Reservation reservation2;

    @Autowired
    private ReservationRepository reservationRepository;

    private static Stream<Arguments> createReservation() {
        return Stream.of(
            Arguments.of(reservation1),
            Arguments.of(reservation2)
        );
    }

    @BeforeAll
    void setUp() {
        reservation1 = Reservation.builder()
            .reservationStatus(ReservationStatus.COMPLETED)
            .customerId(1L)
            .scheduleId(1L)
            .build();

        reservation2 = Reservation.builder()
            .reservationStatus(ReservationStatus.COMPLETED)
            .customerId(2L)
            .scheduleId(2L)
            .build();
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("createReservation")
    @DisplayName("고객은 공연을 예매할 수 있다.")
    void createReservation_Save_Success(Reservation reservation) {
        //given, when
        Long reservationId = reservationRepository.save(reservation);

        //then
        Optional<Reservation> actualReservation = reservationRepository.findById(reservationId);

        assertThat(actualReservation).isNotEmpty();
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("createReservation")
    @DisplayName("고객은 예매한 공연을 취소할 수 있다.")
    void cancelReservation_Update_Success(Reservation reservation) {
        //given
        Long reservationId = reservationRepository.save(reservation);

        //when
        reservationRepository.updateReservationStatusById(reservationId, ReservationStatus.CANCELED);

        //then
        ReservationStatus actualReservationStatus = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ReservationException(RESERVATION_NOT_EXIST, reservationId))
            .getReservationStatus();

        assertThat(actualReservationStatus).isEqualTo(ReservationStatus.CANCELED);
    }
}

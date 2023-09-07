package com.programmers.ticketparis.repository.reservation;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MybatisReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    private static Reservation reservation1;
    private static Reservation reservation2;

    @BeforeAll
    void setUp() {
        reservationRepository.deleteAll();
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
    void createReservation_Save_Sucess(Reservation reservation) {
        // given & when
        reservationRepository.save(reservation);

        // then
        assertThat(reservationRepository.findAll()).hasSize(1);
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("createReservation")
    @DisplayName("고객은 예매한 공연을 취소할 수 있다.")
    void cancleReservation_Update_Success(Reservation reservation) {
        // given
        reservationRepository.save(reservation);

        // when
        Reservation savedReservation = reservationRepository.findAll().get(0);
        reservationRepository.updateById(savedReservation.getReservationId(), ReservationStatus.CANCELED);

        // then
        ReservationStatus actualReservationStatus = reservationRepository.findAll().get(0).getReservationStatus();
        assertThat(actualReservationStatus).isEqualTo(ReservationStatus.CANCELED);
    }

    private static Stream<Arguments> createReservation() {
        return Stream.of(
                Arguments.of(reservation1),
                Arguments.of(reservation2)
        );
    }

}

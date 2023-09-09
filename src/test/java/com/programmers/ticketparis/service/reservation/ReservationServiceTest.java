package com.programmers.ticketparis.service.reservation;

import static org.assertj.core.api.Assertions.*;

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

import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import com.programmers.ticketparis.dto.reservation.request.ReservationCreateRequest;
import com.programmers.ticketparis.dto.reservation.response.ReservationResponse;
import com.programmers.ticketparis.repository.reservation.ReservationRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    private static ReservationCreateRequest reservationCreateRequest1;
    private static ReservationCreateRequest reservationCreateRequest2;

    @BeforeAll
    void setUp() {
        reservationRepository.deleteAll();
        reservationCreateRequest1 = ReservationCreateRequest.builder()
            .customerId(1L)
            .scheduleId(1L)
            .build();

        reservationCreateRequest2 = ReservationCreateRequest.builder()
            .customerId(2L)
            .scheduleId(2L)
            .build();
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("createReservationRequest")
    @DisplayName("고객은 공연을 예매할 수 있다.")
    void createReservation_Save_Sucess(ReservationCreateRequest reservationCreateRequest) {
        // given & when
        reservationService.createReservation(reservationCreateRequest);

        // then
        assertThat(reservationService.findAllReservations()).hasSize(1);
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("createReservationRequest")
    @DisplayName("고객은 예매한 공연을 취소할 수 있다.")
    void cancleReservation_Update_Success(ReservationCreateRequest reservationCreateRequest) {
        // given
        reservationService.createReservation(reservationCreateRequest);

        // when
        ReservationResponse savedReservationResponse = reservationService.findAllReservations().get(0);
        reservationService.cancelReservationById(savedReservationResponse.getReservationId());

        // then
        ReservationStatus actualReservationStatus = reservationService.findAllReservations()
            .get(0)
            .getReservationStatus();
        assertThat(actualReservationStatus).isEqualTo(ReservationStatus.CANCELED);
    }

    private static Stream<Arguments> createReservationRequest() {
        return Stream.of(
            Arguments.of(reservationCreateRequest1),
            Arguments.of(reservationCreateRequest2)
        );
    }
}

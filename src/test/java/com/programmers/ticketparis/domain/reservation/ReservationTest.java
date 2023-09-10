package com.programmers.ticketparis.domain.reservation;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.programmers.ticketparis.exception.ReservationException;

class ReservationTest {

    @Test
    @DisplayName("예매를 생성 할 수 있다.")
    void createReservation_Success() {
        // given & when
        Reservation reservation = Reservation.builder()
            .reservationStatus(ReservationStatus.COMPLETED)
            .scheduleId(1L)
            .customerId(1L)
            .build();

        // then
        assertThat(reservation).isInstanceOf(Reservation.class);
    }

    @Test
    @DisplayName("예매 상태는 예매 완료와 예매 취소가 아닌 경우 예외를 발생시킨다.")
    void anotherReservationStatus_Exception() {
        // given & when & then
        assertThatThrownBy(() -> ReservationStatus.valueOf("Proceeding"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("예매를 생성할 때는 항상 예매 상태가 완료여야하며 예매 취소일 경우 예외가 발생한다.")
    void notCompletedReservationStatus_Exception() {
        // given & when & then
        assertThatThrownBy(() -> Reservation.builder()
            .reservationStatus(ReservationStatus.CANCELED)
            .scheduleId(1L)
            .customerId(1L)
            .build()).isInstanceOf(ReservationException.class).hasMessage("존재하지 않는 예약 상태");
    }
}

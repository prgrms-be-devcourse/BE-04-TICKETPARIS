package com.programmers.ticketparis.domain.reservation;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.time.LocalDateTime;

import com.programmers.ticketparis.exception.ReservationException;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    private Long reservationId;

    @NotNull
    private ReservationStatus reservationStatus;

    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @NotNull
    private Long customerId;

    @NotNull
    private Long scheduleId;

    @Builder
    private Reservation(ReservationStatus reservationStatus, Long customerId, Long scheduleId) {
        validateReservationStatus(reservationStatus);
        this.reservationStatus = reservationStatus;
        this.customerId = customerId;
        this.scheduleId = scheduleId;
    }

    private void validateReservationStatus(ReservationStatus reservationStatus) {
        if (reservationStatus != ReservationStatus.COMPLETED) {
            throw new ReservationException(RESERVATION_STATUS_INVALID, reservationStatus);
        }
    }
}

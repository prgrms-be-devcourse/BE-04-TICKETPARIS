package com.programmers.ticketparis.dto.reservation.response;

import java.time.LocalDateTime;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponse {

    private Long reservationId;
    private ReservationStatus reservationStatus;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;
    private Long customerId;
    private Long scheduleId;

    @Builder
    private ReservationResponse(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.reservationStatus = reservation.getReservationStatus();
        this.createdDatetime = reservation.getCreatedDatetime();
        this.updatedDatetime = reservation.getUpdatedDatetime();
        this.customerId = reservation.getCustomerId();
        this.scheduleId = reservation.getScheduleId();
    }

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(reservation);
    }
}

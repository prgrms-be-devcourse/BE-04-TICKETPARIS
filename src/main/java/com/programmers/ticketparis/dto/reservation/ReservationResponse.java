package com.programmers.ticketparis.dto.reservation;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponse {

    private Long reservationId;
    private ReservationStatus reservationStatus;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @Builder
    private ReservationResponse(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.reservationStatus = reservation.getReservationStatus();
        this.createdDatetime = reservation.getCreatedDatetime();
        this.updatedDatetime = reservation.getUpdatedDatetime();
    }

    public static ReservationResponse of(Reservation reservation) {
        return new ReservationResponse(reservation);
    }
}

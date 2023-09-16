package com.programmers.ticketparis.reservation.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponse {

    private Long reservationId;
    private ReservationStatus reservationStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
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

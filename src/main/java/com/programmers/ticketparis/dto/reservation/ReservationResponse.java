package com.programmers.ticketparis.dto.reservation;

import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationResponse {

    private Long reservationId;
    private ReservationStatus reservationStatus;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @Builder
    public ReservationResponse(Long reservationId, ReservationStatus reservationStatus, LocalDateTime createdDatetime, LocalDateTime updatedDatetime) {
        this.reservationId = reservationId;
        this.reservationStatus = reservationStatus;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }
}

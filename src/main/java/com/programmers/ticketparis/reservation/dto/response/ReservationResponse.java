package com.programmers.ticketparis.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "예매 조회 시 응답 json data 필드값")
public class ReservationResponse {

    @Schema(description = "예매 ID")
    private Long reservationId;

    @Schema(description = "예매 상태")
    private ReservationStatus reservationStatus;

    @Schema(description = "예매 생성 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @Schema(description = "예매 수정 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

    @Schema(description = "고객 ID")
    private Long customerId;

    @Schema(description = "스케줄 ID")
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

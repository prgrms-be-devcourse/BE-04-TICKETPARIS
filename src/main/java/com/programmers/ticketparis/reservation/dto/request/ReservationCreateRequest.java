package com.programmers.ticketparis.reservation.dto.request;

import com.programmers.ticketparis.reservation.domain.Reservation;
import com.programmers.ticketparis.reservation.domain.ReservationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "예매 생성 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationCreateRequest {

    @Schema(description = "고객 ID")
    @NotNull(message = "고객 ID는 필수로 입력")
    @Positive(message = "고객 ID는 1이상의 양수로 입력")
    private Long customerId;

    @Schema(description = "스케줄 ID")
    @NotNull(message = "스케줄 ID는 필수로 입력")
    @Positive(message = "스케줄 ID는 1이상의 양수로 입력")
    private Long scheduleId;

    @Builder
    private ReservationCreateRequest(Long customerId, Long scheduleId) {
        this.customerId = customerId;
        this.scheduleId = scheduleId;
    }

    public Reservation toEntity() {
        return Reservation.builder()
            .reservationStatus(ReservationStatus.COMPLETED)
            .customerId(customerId)
            .scheduleId(scheduleId)
            .build();
    }
}

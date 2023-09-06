package com.programmers.ticketparis.dto.reservation;

import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.domain.reservation.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationCreateRequest {

    @NotNull(message = "고객 ID는 빈 값일 수 없습니다.")
    @Positive(message = "고객 ID는 1이상의 양수 값 이어야합니다.")
    private Long customerId;

    @NotNull(message = "스케줄 ID는 빈 값일 수 없습니다.")
    @Positive(message = "스케줄 ID는 1이상의 양수 값 이어야합니다.")
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

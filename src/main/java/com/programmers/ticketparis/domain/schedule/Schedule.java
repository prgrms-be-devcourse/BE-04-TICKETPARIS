package com.programmers.ticketparis.domain.schedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    private Long scheduleId;

    @NotNull
    private LocalDateTime startDatetime;

    @NotNull
    private Integer sequence;

    @NotNull
    private Integer seatsCount;

    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @NotNull
    private Long performanceId;

    @Builder
    private Schedule(LocalDateTime startDatetime, Integer sequence, Integer seatsCount, Long performanceId) {
        this.startDatetime = startDatetime;
        this.sequence = sequence;
        this.seatsCount = seatsCount;
        this.performanceId = performanceId;
    }
}

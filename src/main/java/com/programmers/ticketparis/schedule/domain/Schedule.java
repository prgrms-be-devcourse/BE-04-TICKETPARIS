package com.programmers.ticketparis.schedule.domain;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.time.LocalDateTime;

import com.programmers.ticketparis.schedule.exception.ScheduleException;

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

    public void decreaseSeatsCount() {
        if (seatsCount == 0) {
            throw new ScheduleException(SCHEDULE_NO_SEATS, seatsCount);
        }

        seatsCount -= 1;
    }

    public void increaseSeatsCount(Integer totalSeatsCount) {
        if (seatsCount == totalSeatsCount) {
            throw new ScheduleException(SCHEDULE_FULL_SEATS, seatsCount);
        }

        seatsCount += 1;
    }
}

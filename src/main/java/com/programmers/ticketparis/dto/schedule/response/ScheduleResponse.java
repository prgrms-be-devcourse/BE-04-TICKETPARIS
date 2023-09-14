package com.programmers.ticketparis.dto.schedule.response;

import java.time.LocalDateTime;

import com.programmers.ticketparis.domain.schedule.Schedule;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleResponse {

    private Long scheduleId;
    private LocalDateTime startDatetime;
    private Integer sequence;
    private Integer seatsCount;
    private Long performanceId;

    @Builder
    private ScheduleResponse(Long scheduleId, LocalDateTime startDatetime, Integer sequence, Integer seatsCount,
        Long performanceId) {
        this.scheduleId = scheduleId;
        this.startDatetime = startDatetime;
        this.sequence = sequence;
        this.seatsCount = seatsCount;
        this.performanceId = performanceId;
    }

    public static ScheduleResponse from(Schedule schedule) {
        return ScheduleResponse.builder()
            .scheduleId(schedule.getScheduleId())
            .startDatetime(schedule.getStartDatetime())
            .sequence(schedule.getSequence())
            .seatsCount(schedule.getSeatsCount())
            .performanceId(schedule.getPerformanceId())
            .build();
    }
}

package com.programmers.ticketparis.schedule.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.schedule.domain.Schedule;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleResponse {

    private Long scheduleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDatetime;

    private Integer sequence;
    private Integer seatsCount;
    private Long performanceId;

    @Builder
    private ScheduleResponse(
        Long scheduleId,
        LocalDateTime startDatetime,
        Integer sequence,
        Integer seatsCount,
        Long performanceId
    ) {
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

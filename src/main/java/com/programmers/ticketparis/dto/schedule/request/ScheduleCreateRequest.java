package com.programmers.ticketparis.dto.schedule.request;

import java.time.LocalDateTime;

import com.programmers.ticketparis.domain.schedule.Schedule;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleCreateRequest {

    private LocalDateTime startDatetime;
    private Integer sequence;

    public Schedule toEntity(Integer seatsCount, Long performanceId) {
        return Schedule.builder()
            .startDatetime(startDatetime)
            .sequence(sequence)
            .seatsCount(seatsCount)
            .performanceId(performanceId)
            .build();
    }
}

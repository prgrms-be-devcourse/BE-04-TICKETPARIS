package com.programmers.ticketparis.dto.schedule.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.domain.schedule.Schedule;

import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleCreateRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDatetime;

    @Positive
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

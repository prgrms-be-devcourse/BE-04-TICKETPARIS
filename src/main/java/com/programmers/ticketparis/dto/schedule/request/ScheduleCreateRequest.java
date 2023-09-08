package com.programmers.ticketparis.dto.schedule.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.domain.schedule.Schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleCreateRequest {

    @NotNull(message = "스케줄 시작 날짜와 시간이 입력되지 않음")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDatetime;

    @Positive(message = "회차는 자연수만 가능")
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

package com.programmers.ticketparis.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.schedule.domain.Schedule;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleCreateRequest {

    @NotNull(message = "스케줄 시작 날짜와 시간은 필수로 입력")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDatetime;

    @Positive(message = "회차는 1이상의 양수로 입력")
    private Integer sequence;

    @Positive(message = "공연 ID는 1이상의 양수로 입력")
    private Long performanceId;

    public Schedule toEntity(Integer seatsCount) {
        return Schedule.builder()
            .startDatetime(startDatetime)
            .sequence(sequence)
            .seatsCount(seatsCount)
            .performanceId(performanceId)
            .build();
    }
}

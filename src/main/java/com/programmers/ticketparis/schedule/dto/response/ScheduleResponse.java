package com.programmers.ticketparis.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.schedule.domain.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "스케줄 조회 시 응답 json data 필드값")
public class ScheduleResponse {

    @Schema(description = "스케줄 ID")
    private Long scheduleId;

    @Schema(description = "스케줄 시작 날자 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDatetime;

    @Schema(description = "헤딩 공연 회차")
    private Integer sequence;

    @Schema(description = "좌석 수")
    private Integer seatsCount;

    @Schema(description = "공연 ID")
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

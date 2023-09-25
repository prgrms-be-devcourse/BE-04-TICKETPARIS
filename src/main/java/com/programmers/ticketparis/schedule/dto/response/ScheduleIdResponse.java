package com.programmers.ticketparis.schedule.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "from")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "공연 생성,수정 시 응답 json data 필드값")
public class ScheduleIdResponse {

    @Schema(description = "스케줄 ID")
    private Long scheduleId;
}

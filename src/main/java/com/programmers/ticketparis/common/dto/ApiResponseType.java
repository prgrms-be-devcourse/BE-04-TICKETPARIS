package com.programmers.ticketparis.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.programmers.ticketparis.auth.dto.LoginSuccessResponse;
import com.programmers.ticketparis.auth.dto.LogoutSuccessResponse;
import com.programmers.ticketparis.auth.dto.SessionValueDto;
import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.dto.response.CustomerIdResponse;
import com.programmers.ticketparis.member.dto.response.CustomerResponse;
import com.programmers.ticketparis.member.dto.response.SellerIdResponse;
import com.programmers.ticketparis.member.dto.response.SellerResponse;
import com.programmers.ticketparis.performance.dto.response.PerformanceIdResponse;
import com.programmers.ticketparis.performance.dto.response.PerformanceResponse;
import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.reservation.dto.response.ReservationIdResponse;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;
import com.programmers.ticketparis.schedule.dto.response.ScheduleIdResponse;
import com.programmers.ticketparis.schedule.dto.response.ScheduleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "공통 API 응답 포맷")
public class ApiResponseType<T> {

    @Schema(description = "응답 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @JsonUnwrapped
    @Schema(description = "응답 경로")
    private final String path;

    @Schema(description = "응답 data", oneOf = {PerformanceResponse.class, PerformanceIdResponse.class,
        ScheduleResponse.class, ScheduleIdResponse.class,
        RankingResponse.class, SessionValueDto.class,
        ReservationResponse.class, ReservationIdResponse.class,
        CustomerResponse.class, CustomerIdResponse.class,
        SellerResponse.class, SellerIdResponse.class,
        LoginSuccessResponse.class, LogoutSuccessResponse.class
    })
    private final T data;

    @Schema(description = "응답 메시지(정상 응답 시 null, 실패 시 Business 예외 매시지 출력)", oneOf = ExceptionRule.class, defaultValue = "null")
    private final String message;

    @Builder
    private ApiResponseType(String path, T data, String message) {
        this.path = path;
        this.data = data;
        this.message = message;
    }
}

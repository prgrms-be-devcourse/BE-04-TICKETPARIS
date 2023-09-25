package com.programmers.ticketparis.ranking.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "랭킹 조회 시 응답 json data 필드값 ")
public class RankingResponse implements Serializable {

    @Schema(description = "공연 ID")
    private Long performanceId;

    @Schema(description = "공연 제목")
    private String title;

    @Schema(description = "공연 포스터 URL")
    private String posterUrl;

    @Schema(description = "공연 시작 날짜")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    @Schema(description = "공연 종료 날짜")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

    @Schema(description = "공연장 명")
    private String hallName;

    @Schema(description = "예매율")
    private Double reservationRate;

    @Schema(description = "랭킹 순위")
    private Integer ranking;
}

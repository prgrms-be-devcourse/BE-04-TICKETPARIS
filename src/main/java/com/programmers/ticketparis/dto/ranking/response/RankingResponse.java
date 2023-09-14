package com.programmers.ticketparis.dto.ranking.response;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankingResponse {

    private Long performanceId;
    private String title;
    private String posterUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private String hallName;
    private Double reservationRate;
    private Integer ranking;
}

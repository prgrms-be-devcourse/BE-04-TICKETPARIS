package com.programmers.ticketparis.ranking.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankingResponse implements Serializable {

    private Long performanceId;
    private String title;
    private String posterUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private String hallName;
    private Double reservationRate;
    private Integer ranking;
}

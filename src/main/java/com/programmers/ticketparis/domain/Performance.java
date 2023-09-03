package com.programmers.ticketparis.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Performance {
    private Long performanceId;
    private String title;
    private String posterUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private Integer ageRating;
    private Long price;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime updateDateTime;

    @Builder
    private Performance(String title, String posterUrl, LocalDate startDate, LocalDate endDate,
                        String duration, Integer ageRating, Long price, String category,
                        LocalDateTime createdDateTime, LocalDateTime updateDateTime) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.ageRating = ageRating;
        this.price = price;
        this.category = category;
        this.createdDateTime = createdDateTime;
        this.updateDateTime = updateDateTime;
    }
}

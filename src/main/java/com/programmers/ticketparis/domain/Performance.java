package com.programmers.ticketparis.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Performance {

    private Long performanceId;

    @NotNull
    private String title;

    @NotNull
    private String posterUrl;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private String duration;

    @NotNull
    private Integer ageRating;

    @NotNull
    private Long price;

    @NotNull
    private Category category;

    private LocalDateTime createdDateTime;

    private LocalDateTime updateDateTime;

    @Builder
    private Performance(String title, String posterUrl, LocalDate startDate, LocalDate endDate,
                        String duration, Integer ageRating, Long price, Category category,
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

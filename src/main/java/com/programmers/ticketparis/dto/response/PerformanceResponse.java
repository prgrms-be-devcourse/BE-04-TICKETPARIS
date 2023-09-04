package com.programmers.ticketparis.dto.response;

import com.programmers.ticketparis.domain.Performance;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PerformanceResponse {
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
    private LocalDateTime updatedDateTime;

    @Builder
    public PerformanceResponse(Long performanceId, String title, String posterUrl, LocalDate startDate, LocalDate endDate,
                               String duration, Integer ageRating, Long price, String category,
                               LocalDateTime createdDateTime, LocalDateTime updateDateTime) {
        this.performanceId = performanceId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.ageRating = ageRating;
        this.price = price;
        this.category = category;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updateDateTime;
    }

    public static PerformanceResponse fromEntity(Performance performance) {
        return PerformanceResponse.builder()
                .performanceId(performance.getPerformanceId())
                .title(performance.getTitle())
                .posterUrl(performance.getPosterUrl())
                .startDate(performance.getStartDate())
                .endDate(performance.getEndDate())
                .duration(performance.getDuration())
                .ageRating(performance.getAgeRating())
                .price(performance.getPrice())
                .category(String.valueOf(performance.getCategory()))
                .createdDateTime(performance.getCreatedDateTime())
                .updateDateTime(performance.getUpdateDateTime())
                .build();
    }
}

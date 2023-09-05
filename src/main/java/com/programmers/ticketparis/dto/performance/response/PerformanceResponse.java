package com.programmers.ticketparis.dto.performance.response;

import com.programmers.ticketparis.domain.performance.Performance;
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
    private Integer price;
    private String category;
    private String description;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Long sellerId;
    private Long hallId;

    @Builder
    public PerformanceResponse(Long performanceId, String title, String posterUrl, LocalDate startDate,
                               LocalDate endDate, String duration, Integer ageRating, Integer price, String category, String description,
                               LocalDateTime createdDateTime, LocalDateTime updateDateTime, Long sellerId, Long hallId) {
        this.performanceId = performanceId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.ageRating = ageRating;
        this.price = price;
        this.category = category;
        this.description = description;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updateDateTime;
        this.sellerId = sellerId;
        this.hallId = hallId;
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
                .description(performance.getDescription())
                .createdDateTime(performance.getCreatedDatetime())
                .updateDateTime(performance.getUpdatedDatetime())
                .sellerId(performance.getSellerId())
                .hallId(performance.getHallId())
                .build();
    }
}

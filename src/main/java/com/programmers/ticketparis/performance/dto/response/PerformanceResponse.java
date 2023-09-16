package com.programmers.ticketparis.performance.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.domain.Performance;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceResponse {

    private Long performanceId;
    private String title;
    private String posterUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private Integer ageRating;
    private Integer price;
    private Category category;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDateTime;

    private Long sellerId;
    private Long hallId;

    @Builder
    private PerformanceResponse(
        Long performanceId,
        String title,
        String posterUrl,
        LocalDate startDate,
        LocalDate endDate,
        String duration,
        Integer ageRating,
        Integer price,
        Category category,
        String description,
        LocalDateTime createdDateTime,
        LocalDateTime updateDateTime,
        Long sellerId,
        Long hallId
    ) {
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
            .category(performance.getCategory())
            .description(performance.getDescription())
            .createdDateTime(performance.getCreatedDatetime())
            .updateDateTime(performance.getUpdatedDatetime())
            .sellerId(performance.getSellerId())
            .hallId(performance.getHallId())
            .build();
    }
}

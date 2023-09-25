package com.programmers.ticketparis.performance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.domain.Performance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "공연 조회 시 응답 json data 필드값")
public class PerformanceResponse {

    @Schema(description = "공연 ID")
    private Long performanceId;

    @Schema(description = "공연 제목")
    private String title;

    @Schema(description = "공연 포스터 URL")
    private String posterUrl;

    @Schema(description = "공연 시작 날짜")
    private LocalDate startDate;

    @Schema(description = "공연 종료 날짜")
    private LocalDate endDate;

    @Schema(description = "공연 시간")
    private String duration;

    @Schema(description = "공연 연령 등급")
    private Integer ageRating;

    @Schema(description = "공연 가격")
    private Integer price;

    @Schema(description = "공연 카테고리")
    private Category category;

    @Schema(description = "공연 관련 설명")
    private String description;

    @Schema(description = "공연 생성 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDateTime;

    @Schema(description = "공연 수정 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDateTime;

    @Schema(description = "판매자 ID")
    private Long sellerId;

    @Schema(description = "공연장 ID")
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

package com.programmers.ticketparis.performance.dto.request;

import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.domain.Performance;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Schema(description = "공연 생성 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceCreateRequest {

    @Schema(description = "공연 제목")
    @NotBlank(message = "공연의 제목은 필수로 입력")
    @Size(min = 1, max = 100, message = "공연 제목은 1이상 100 이하로 입력")
    private String title;

    @Schema(description = "공연 포스터 @Schema(description = \"공연 생성 요청\")\nURL")
    @NotBlank(message = "공연 포스터 URL은 필수로 입력")
    @Size(max = 500, message = "공연 포스터 URL은 500자 이하로 입력")
    private String posterUrl;

    @Schema(description = "공연 시작 날짜")
    @NotNull(message = "공연의 시작 날짜는 필수로 입력")
    private LocalDate startDate;

    @Schema(description = "공연 종료 날짜")
    @NotNull(message = "공연의 종료 날짜는 필수로 입력")
    private LocalDate endDate;

    @Schema(description = "공연 시간")
    @NotBlank(message = "공연 시간은 필수로 입력")
    @Size(max = 10, message = "공연 시간은 10글자 이하로 입력")
    private String duration;

    @Schema(description = "공연 연령 등급")
    @NotNull(message = "연령 정보는 필수로 입력")
    @PositiveOrZero(message = "연령 등급는 0이상으로 입력")
    @Max(value = 19, message = "연령 등급 19세 이하로 입력")
    private Integer ageRating;

    @Schema(description = "공연 가격")
    @NotNull(message = "가격 정보는 필수로 입력")
    @PositiveOrZero(message = "가격의 정보는 0이상으로 입력")
    private Integer price;


    @Schema(description = "공연 카테고리")
    private Category category;

    @Schema(description = "공연 관련 설명")
    private String description;

    @Schema(description = "판매자 ID")
    @NotNull(message = "판매자 ID는 필수로 입력")
    private Long sellerId;

    @Schema(description = "공연장 ID")
    private Long hallId;

    @Builder
    private PerformanceCreateRequest(String title, String posterUrl, LocalDate startDate, LocalDate endDate,
                                     String duration, Integer ageRating, Integer price, Category category, String description, Long sellerId,
                                     Long hallId) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.ageRating = ageRating;
        this.price = price;
        this.category = category;
        this.description = description;
        this.sellerId = sellerId;
        this.hallId = hallId;
    }

    public Performance toEntity() {
        return Performance.builder()
            .title(title)
            .posterUrl(posterUrl)
            .startDate(startDate)
            .endDate(endDate)
            .duration(duration)
            .ageRating(ageRating)
            .price(price)
            .category(category)
            .description(description)
            .sellerId(sellerId)
            .hallId(hallId)
            .build();
    }
}

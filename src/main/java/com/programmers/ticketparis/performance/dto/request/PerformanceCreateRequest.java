package com.programmers.ticketparis.performance.dto.request;

import java.time.LocalDate;

import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.domain.Performance;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceCreateRequest {

    @NotBlank(message = "공연의 제목은 필수로 입력")
    @Size(min = 1, max = 100, message = "공연 제목은 1이상 100 이하로 입력")
    private String title;

    @NotBlank(message = "공연 포스터 URL은 필수로 입력")
    @Size(max = 500, message = "공연 포스터 URL은 500자 이하로 입력")
    private String posterUrl;

    @NotNull(message = "공연의 시작 날짜는 필수로 입력")
    private LocalDate startDate;

    @NotNull(message = "공연의 종료 날짜는 필수로 입력")
    private LocalDate endDate;

    @NotBlank(message = "공연 시간은 필수로 입력")
    @Size(max = 10, message = "공연 시간은 10글자 이하로 입력")
    private String duration;

    @NotNull(message = "연령 정보는 필수로 입력")
    @PositiveOrZero(message = "연령 등급는 0이상으로 입력")
    @Max(value = 19, message = "연령 등급 19세 이하로 입력")
    private Integer ageRating;

    @NotNull(message = "가격 정보는 필수로 입력")
    @PositiveOrZero(message = "가격의 정보는 0이상으로 입력")
    private Integer price;

    private Category category;
    private String description;

    @NotNull(message = "판매자 ID는 필수로 입력")
    private Long sellerId;

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

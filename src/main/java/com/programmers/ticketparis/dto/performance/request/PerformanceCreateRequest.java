package com.programmers.ticketparis.dto.performance.request;

import com.programmers.ticketparis.domain.performance.Category;
import com.programmers.ticketparis.domain.performance.Performance;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PerformanceCreateRequest {

    @NotBlank(message = "공연의 제목은 필수입니다.")
    @Size(min = 1, max = 100, message = "공연 제목은 1이상 100 이하의 글자를 입력해주세요!")
    private String title;

    @NotBlank(message = "공연 포스터의 URL은 필수입니다.")
    @Size(max = 500, message = "공연 포스터의 URL은 500자 이하로 입력해주세요.")
    private String posterUrl;

    @NotNull(message = "공연의 시작 날짜는 비어둘 수 없습니다!")
    private LocalDate startDate;

    @NotNull(message = "공연의 종료 날짜는 비어둘 수 없습니다!")
    private LocalDate endDate;

    @NotBlank(message = "공연 시간은 필수입니다.")
    @Size(max = 10, message = "공연 시간은 10글자 이하만 입력 가능합니다")
    private String duration;

    @NotNull(message = "연령 정보는 필수입니다.")
    @PositiveOrZero(message = "연령 등급는 0이상 이어야 합니다.")
    @Max(value = 19, message = "연령 등급 19세 이하까지 입니다.")
    private Integer ageRating;

    @NotNull(message = "가격 정보는 필수입니다.")
    @PositiveOrZero(message = "가격의 정보는 0이상 이어야 합니다.")
    private Integer price;

    private Category category;

    private String description;

    @NotNull(message = "판매자의 ID 정보는 필수입니다.")
    private Long sellerId;

    private Long hallId;

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

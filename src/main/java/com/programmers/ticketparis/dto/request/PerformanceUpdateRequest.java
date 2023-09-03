package com.programmers.ticketparis.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceUpdateRequest {

    @NotNull(message = "공연의 제목은 비어있으면 안됩니다!")
    @Size(min = 1, max = 100, message = "공연 제목은 1이상 100 이하의 글자를 입력해주세요!")
    private String title;

    @Size(max = 500, message = "포스터 URL은 500자 이하로 입력해주세요.")
    private String posterUrl;

    @NotNull(message = "공연의 시작 날짜는 비어둘 수 없습니다!")
    private LocalDate startDate;

    @NotNull(message = "공연의 종료 날짜는 비어둘 수 없습니다!")
    private LocalDate endDate;

    @Size(max = 10, message = "공연 시간은 10글자 이하만 입력 가능합니다")
    private String duration;

    @Min(value = 0, message = "연령 등급은 0보다 커야 합니다.")
    @Max(value = 19, message = "연령 등급은 19세 이하여야 합니다.")
    private Integer ageRating;

    @Min(value = 0, message = "공연 가격은 0 이상 이여야 합니다.")
    private Long price;
    private String category;
    private LocalDateTime updateDateTime;

    public boolean isValidDates() {
        return !startDate.isAfter(endDate);
    }
}

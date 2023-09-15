package com.programmers.ticketparis.domain.performance;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.programmers.ticketparis.exception.PerformanceException;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Integer price;

    @NotNull
    private Category category;

    private String description;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    @NotNull
    private Long sellerId;

    private Long hallId;

    @Builder
    private Performance(String title, String posterUrl, LocalDate startDate, LocalDate endDate, String duration,
        Integer ageRating, Integer price, Category category,
        String description, Long sellerId, Long hallId) {

        validatePerformanceDates(startDate, endDate);

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

    private void validatePerformanceDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new PerformanceException(PERFORMANCE_START_DATE_AFTER_END_DATE,
                List.of(String.valueOf(startDate), String.valueOf(endDate)));
        }
    }
}

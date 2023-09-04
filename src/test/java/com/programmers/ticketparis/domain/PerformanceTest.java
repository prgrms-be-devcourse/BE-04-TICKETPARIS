package com.programmers.ticketparis.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.programmers.ticketparis.domain.Category.MUSICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(OrderAnnotation.class)
class PerformanceTest {

    @Order(1)
    @Test
    @DisplayName("공연 생성 테스트 😀")
    void createPerformanceTest() {
        //given
        Long perfomanceId = 1L;
        String title = "테스트 공연 타이틀";
        String posterUrl = "https://picsum.photos/id/237/200/300\n";
        LocalDate startDate = LocalDate.of(2023, 9, 4);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        String duration = "2시간";
        Integer ageRating = 18;
        Long price = 15000L;
        Category category = MUSICAL;
        LocalDateTime createdDateTime = LocalDateTime.now();
        LocalDateTime updatedDateTime = LocalDateTime.now();

        //when
        Performance performance = new Performance(perfomanceId, title, posterUrl, startDate, endDate,
                duration, ageRating, price,
                category, createdDateTime, updatedDateTime);

        //then
        assertThat(performance)
                .usingRecursiveComparison()
                .isEqualTo(new Performance(perfomanceId,
                        title, posterUrl, startDate, endDate, duration, ageRating, price, category, createdDateTime, updatedDateTime));
    }

    @Order(2)
    @Test
    @DisplayName("공연 생성 실패 테스트(지정하지 않은 Category가 들어올때) 😱")
    void invalidCategory() {
        //given
        Long performanceId = 1L;
        String title = "테스트 공연 타이틀";
        String posterUrl = "https://picsum.photos/id/237/200/300\n";
        LocalDate startDate = LocalDate.of(2023, 9, 4);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        String duration = "2시간";
        Integer ageRating = 18;
        Long price = 15000L;
        String invalidCategoryString = "등록되지 않은 카테고리";
        LocalDateTime createdDateTime = LocalDateTime.now();
        LocalDateTime updatedDateTime = LocalDateTime.now();

        //When - Then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Category category = Category.fromString(invalidCategoryString);
                    new Performance(performanceId, title, posterUrl, startDate,
                            endDate, duration, ageRating, price,
                            category, createdDateTime, updatedDateTime);
                }
        );
    }
}

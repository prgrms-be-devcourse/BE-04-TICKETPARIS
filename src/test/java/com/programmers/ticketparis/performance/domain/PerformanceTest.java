package com.programmers.ticketparis.performance.domain;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;
import static com.programmers.ticketparis.performance.domain.Category.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.programmers.ticketparis.performance.exception.PerformanceException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformanceTest {

    @Test
    @Order(1)
    @DisplayName("공연 도메인 필드에 정상적으로 입력이 되었을 때, 공연이 생성된다.")
    void createPerformanceTest() {
        //given, when
        Performance testPerformance = Performance.builder()
            .title("테스트 공연 타이틀")
            .posterUrl("https://picsum.photos/id/237/200/300\n")
            .startDate(LocalDate.of(2023, 9, 4))
            .endDate(LocalDate.of(2023, 12, 31))
            .duration("2시간")
            .ageRating(18)
            .price(15000)
            .category(MUSICAL)
            .description("테스트 공연의 상세 설명입니다.")
            .sellerId(1L)
            .hallId(2L)
            .build();

        //then
        assertThat(testPerformance).usingRecursiveComparison().isEqualTo(testPerformance);
    }

    @Order(2)
    @Test
    @DisplayName("존재하지 않는 공연 카테고리에 대해 valueOf를 호출할 경우 예외를 발생시킨다.")
    void invalidCategory() {
        //given, when
        String invalidCategoryString = "지정되지 않은 카테고리";

        //then
        assertThatThrownBy(() -> Category.valueOf(invalidCategoryString)).isInstanceOf(IllegalArgumentException.class);
    }

    @Order(3)
    @Test
    @DisplayName("존재하지 않는 공연 카테고리의 문자열에 대해 fromString을 호출할 경우 예외를 발생시킨다.")
    void invalidCategoryFromString() {
        //given, when
        String invalidCategoryString = "지정되지 않은 카테고리";

        //then
        assertThatThrownBy(() -> Category.fromString(invalidCategoryString))
            .isInstanceOf(PerformanceException.class)
            .hasMessage(PERFORMANCE_CATEGORY_INVALID.getMessage());
    }
}

package com.programmers.ticketparis.domain.performance;

import static com.programmers.ticketparis.domain.performance.Category.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PerformanceTest {

    @Order(1)
    @Test
    @DisplayName("공연 도메인 필드에 정상적으로 입력이 되었을 때, 공연이 생성되는지 테스트")
    void createPerformanceTest() {

        //given & when
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
            .hallId(2L).build();

        //then
        assertThat(testPerformance).usingRecursiveComparison().isEqualTo(testPerformance);

    }

    @Order(2)
    @Test
    @DisplayName("공연 Category필드가 Enum이 아닐때, 공연 생성이 실패되는지 테스트")
    void invalidCategory() {
        //given & when
        String invalidCategoryString = "지정되지 않은 카테고리";

        //then
        assertThatThrownBy(() -> {
                Category category = Category.valueOf(invalidCategoryString);
                Performance testPerformance = Performance.builder()
                    .title("테스트 공연 타이틀")
                    .posterUrl("https://picsum.photos/id/237/200/300")
                    .startDate(LocalDate.of(2023, 9, 4))
                    .endDate(LocalDate.of(2023, 12, 31))
                    .duration("2시간")
                    .ageRating(18)
                    .price(15000)
                    .category(Category.valueOf(invalidCategoryString))
                    .description("테스트 공연의 상세 설명입니다.")
                    .sellerId(1L)
                    .hallId(2L).build();
            }
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

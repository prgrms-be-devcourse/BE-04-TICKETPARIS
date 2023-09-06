package com.programmers.ticketparis.domain.performance;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static com.programmers.ticketparis.domain.performance.Category.MUSICAL;
import static com.programmers.ticketparis.domain.performance.Category.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PerformanceTest {

    @Order(1)
    @Test
    @DisplayName("공연 생성 테스트 😀")
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
        Performance expectedPerformance = Performance.builder()
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

        assertThat(testPerformance)
                .usingRecursiveComparison()
                .isEqualTo(expectedPerformance);
    }

    @Order(2)
    @Test
    @DisplayName("공연 생성 실패 테스트(지정하지 않은 Category가 들어올때) 😱")
    void invalidCategory() {
        //given & when
        String invalidCategoryString = "지정되지 않은 카테고리";

        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Category category = valueOf(invalidCategoryString);
                    Performance testPerformance = Performance.builder()
                            .title("테스트 공연 타이틀")
                            .posterUrl("https://picsum.photos/id/237/200/300\n")
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
        );
    }
}

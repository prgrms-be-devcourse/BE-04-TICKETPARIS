package com.programmers.ticketparis.repository.performance;

import com.programmers.ticketparis.domain.performance.Performance;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.programmers.ticketparis.domain.performance.Category.MUSICAL;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformanceRepositoryTest {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Order(1)
    @Test
    @DisplayName("😀 정상적으로 공연이 레포지토리에 저장되는지 테스트")
    void SuccessSavePerformanceTest() {
        //given
        Performance testPerformance = Performance.builder()
                .title("테스트 공연 타이틀")
                .posterUrl("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224812_230829_130237.gif")
                .startDate(LocalDate.of(2023, 9, 11))
                .endDate(LocalDate.of(2023, 12, 31))
                .duration("2시간")
                .price(15000)
                .ageRating(15)
                .category(MUSICAL)
                .description("테스트 공연 상세 설명")
                .sellerId(1L)
                .hallId(1L)
                .build();

        //when
        performanceRepository.save(testPerformance);

        //then
        try {
            performanceRepository.findById(7L);
        } catch (IllegalArgumentException e) {
            System.out.println("예외 발생 메시지 = " + e.getMessage());
        }
    }

//    @Order(2)
//    @Test
//    @DisplayName("😀 정상적으로 레포지토리에 저장된 공연이 수정되는지 테스트")
//    void SucessUpdatePerfomanceTest() {
//        //given
//        Performance testPerformance = Performance.builder()
//                .title("테스트 공연 타이틀")
//                .posterUrl("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224812_230829_130237.gif")
//                .startDate(LocalDate.of(2023, 9, 11))
//                .endDate(LocalDate.of(2023, 12, 31))
//                .duration("2시간")
//                .price(15000)
//                .ageRating(15)
//                .category(MUSICAL)
//                .description("테스트 공연 상세 설명")
//                .sellerId(1L)
//                .hallId(1L)
//                .build();
//
//        performanceRepository.save(testPerformance);
//
//        //when
////        PerformanceUpdateRequest updateRequest =
//
//
//        //then
//
//    }

//    @Order(3)
//    @Test
//    @DisplayName("😀 저장된 공연을 ID를 통해 조회하는 테스트")
//    void findPerformanceByIdTest() {
//
//    }
//
//    @Order(4)
//    @Test
//    @DisplayName("😀 저장된 모든 공연을 조회하는 테스트")
//    void findAllPerformanceTest() {
//
//    }
//
//    @Order(5)
//    @Test
//    @DisplayName("😀 저장된 공연 중 특정 공연을 삭제하는 테스트")
//    void deletePerformanceByIdTest() {
//
//    }
}

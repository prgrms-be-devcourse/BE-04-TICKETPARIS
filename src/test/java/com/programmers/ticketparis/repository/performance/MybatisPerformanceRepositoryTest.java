package com.programmers.ticketparis.repository.performance;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.domain.performance.Category;
import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.PerformanceException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MybatisPerformanceRepositoryTest {

    private static Performance testPerformance1;
    private static Performance testPerformance2;

    @Autowired
    private MybatisPerformanceRepository mybatisPerformanceRepository;

    @BeforeAll
    void setUp() {
        testPerformance1 = Performance.builder()
            .title("테스트 공연 타이틀")
            .posterUrl("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224812_230829_130237.gif")
            .startDate(LocalDate.of(2023, 9, 11))
            .endDate(LocalDate.of(2023, 12, 31))
            .duration("2시간")
            .price(15000)
            .ageRating(15)
            .category(Category.MUSICAL)
            .description("테스트 공연 상세 설명")
            .sellerId(1L)
            .hallId(1L)
            .build();

        testPerformance2 = Performance.builder()
            .title("테스트 공연 타이틀")
            .posterUrl("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224812_230829_130237.gif")
            .startDate(LocalDate.of(2023, 10, 11))
            .endDate(LocalDate.of(2023, 12, 19))
            .duration("3시간")
            .price(30000)
            .ageRating(15)
            .category(Category.MUSICAL)
            .description("테스트 공연 상세 설명")
            .sellerId(1L)
            .hallId(1L)
            .build();
    }

    @Transactional
    @Order(1)
    @Test
    @DisplayName("공연을 레포지토리에 저장할 수 있다.")
    void createPerformanceSaveTest() {
        //given & when
        Long savedPerformanceId = mybatisPerformanceRepository.save(testPerformance1);

        //then
        Performance savePerformance = mybatisPerformanceRepository.findById(savedPerformanceId)
            .orElseThrow(() -> new PerformanceException(ExceptionRule.PERFORMANCE_NOT_EXIST,
                List.of(String.valueOf(savedPerformanceId))));

        assertThat(savePerformance).isNotNull();
    }

    @Order(2)
    @Test
    @DisplayName("레포지토리에 저장된 공연을 ID로 조회한다.")
    void findPerformanceByIdTest() {
        //given & when
        Long findPerformanceById = mybatisPerformanceRepository.save(testPerformance1);

        //when
        Performance foundPerformance = mybatisPerformanceRepository.findById(findPerformanceById)
            .orElseThrow(() -> new PerformanceException(ExceptionRule.PERFORMANCE_NOT_EXIST,
                List.of(String.valueOf(findPerformanceById))));

        //then
        assertThat(foundPerformance).isNotNull();
    }

    @Order(3)
    @Test
    @DisplayName("레포지토리에 저장된 모든 공연을 조회한다.")
    void findAllPerformanceTest() {
        //given
        mybatisPerformanceRepository.save(testPerformance1);
        mybatisPerformanceRepository.save(testPerformance2);

        Pageable pageable = Pageable.builder()
            .pageNum(1)
            .size(10)
            .build();

        //when
        List<Performance> performances = mybatisPerformanceRepository.findPerformancesByPage(pageable);

        //then
        assertThat(performances).isNotNull();
        assertThat(performances.size()).isEqualTo(8);
    }

    @Transactional
    @Order(4)
    @Test
    @DisplayName("레포지토리에 있는 특정 공연을 수정한다.")
    void updatePerformanceByIdTest() {
        //given
        Long savedPerformanceId = mybatisPerformanceRepository.save(testPerformance1);
        PerformanceUpdateRequest performanceUpdateRequest = new PerformanceUpdateRequest(
            "수정된 타이틀",
            "http://www.kopis.or.kr/upload/pfmPoster/PF_PF224754_230828_165705.gif",
            LocalDate.of(2023, 9, 11),
            LocalDate.of(2023, 11, 13),
            "3시간",
            15,
            30000,
            Category.MUSICAL,
            "테스트 설명입니다.",
            1L);

        //when
        mybatisPerformanceRepository.update(savedPerformanceId, performanceUpdateRequest);

        //then
        Performance updatedPerformance = mybatisPerformanceRepository.findById(savedPerformanceId)
            .orElseThrow(() -> new PerformanceException(ExceptionRule.PERFORMANCE_NOT_EXIST,
                List.of(String.valueOf(savedPerformanceId))));

        assertThat(updatedPerformance.getTitle()).isEqualTo(performanceUpdateRequest.getTitle());
    }

    @Transactional
    @Order(5)
    @Test
    @DisplayName("레포지토리에 있는 특정 공연을 삭제한다.")
    void deletePerformanceByIdTest() {
        //given
        Long savedPerfomanceId1 = mybatisPerformanceRepository.save(testPerformance1);
        Long savedPerfomanceId2 = mybatisPerformanceRepository.save(testPerformance2);

        //when
        mybatisPerformanceRepository.deleteById(savedPerfomanceId1);
        mybatisPerformanceRepository.deleteById(savedPerfomanceId2);

        //then
        boolean exists1 = mybatisPerformanceRepository.existsById(savedPerfomanceId1);
        boolean exists2 = mybatisPerformanceRepository.existsById(savedPerfomanceId2);
        assertThat(exists1).isFalse();
        assertThat(exists2).isFalse();
    }
}

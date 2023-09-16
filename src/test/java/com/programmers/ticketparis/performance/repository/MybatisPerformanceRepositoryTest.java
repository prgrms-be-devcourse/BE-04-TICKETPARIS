package com.programmers.ticketparis.performance.repository;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.domain.Performance;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.performance.exception.PerformanceException;
import com.programmers.ticketparis.performance.repository.mybatis.MybatisPerformanceRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MybatisPerformanceRepositoryTest {

    private Performance testPerformance1;
    private Performance testPerformance2;

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

    @Test
    @Order(1)
    @DisplayName("공연을 레포지토리에 저장할 수 있다.")
    @Transactional
    void createPerformanceSaveTest() {
        //given, when
        Long savedPerformanceId = mybatisPerformanceRepository.save(testPerformance1);

        //then
        Optional<Performance> savePerformance = mybatisPerformanceRepository.findById(savedPerformanceId);

        assertThat(savePerformance).isNotEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("레포지토리에 저장된 공연을 ID로 조회한다.")
    void findPerformanceByIdTest() {
        //given, when
        Long findPerformanceById = mybatisPerformanceRepository.save(testPerformance1);

        //when
        Optional<Performance> foundPerformance = mybatisPerformanceRepository.findById(findPerformanceById);

        //then
        assertThat(foundPerformance).isNotEmpty();
    }

    @Test
    @Order(3)
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
        assertThat(performances).hasSize(8);
    }

    @Test
    @Order(4)
    @DisplayName("레포지토리에 있는 특정 공연을 수정한다.")
    @Transactional
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
            .orElseThrow(() -> new PerformanceException(PERFORMANCE_NOT_EXIST, savedPerformanceId));

        assertThat(updatedPerformance.getTitle()).isEqualTo(performanceUpdateRequest.getTitle());
    }

    @Test
    @Order(5)
    @Transactional
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

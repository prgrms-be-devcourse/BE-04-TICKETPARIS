package com.programmers.ticketparis.performance.service;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.performance.domain.Category;
import com.programmers.ticketparis.performance.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.performance.dto.response.PerformanceIdResponse;
import com.programmers.ticketparis.performance.dto.response.PerformanceResponse;
import com.programmers.ticketparis.performance.exception.PerformanceException;
import com.programmers.ticketparis.performance.repository.mybatis.MybatisPerformanceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.programmers.ticketparis.common.exception.ExceptionRule.PERFORMANCE_NOT_EXIST;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformanceServiceTest {

    private PerformanceCreateRequest performanceCreateRequest;
    private PerformanceUpdateRequest performanceUpdateRequest;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private MybatisPerformanceRepository mybatisPerformanceRepository;

    @BeforeAll
    void setUp() {
        performanceCreateRequest = PerformanceCreateRequest.builder()
            .title("제4회 더 싱어즈 정기연주회")
            .posterUrl("http://www.kopis.or.kr/upload/pfmPoster/PF_PF224754_230828_165705.gif")
            .startDate(LocalDate.of(2023, 9, 20))
            .endDate(LocalDate.of(2023, 12, 20))
            .duration("2시간")
            .ageRating(15)
            .price(15000)
            .category(Category.MUSICAL)
            .description("테스트 공연 입니다.")
            .sellerId(1L)
            .hallId(1L).build();

        performanceUpdateRequest = new PerformanceUpdateRequest(
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
    }

    @Test
    @Order(1)
    @Transactional
    @DisplayName("공연을 생성하고, 생성된 공연의 ID를 반환한다.")
    void createPerformanceTest() {
        //given, when
        PerformanceIdResponse performanceIdResponse = performanceService.createPerformance(performanceCreateRequest);

        //then
        assertNotNull(performanceIdResponse);
        assertNotNull(performanceIdResponse.getPerformanceId());
    }

    @Test
    @Order(2)
    @DisplayName("저장된 공연을 ID로 조회한다.")
    void findPerformanceByIdTest() {
        //given
        Long performanceId = 6L;

        //when
        PerformanceResponse foundPerformance = performanceService.findPerformanceById(performanceId);

        //then
        assertNotNull(foundPerformance);
    }

    @Test
    @Order(3)
    @DisplayName("저장된 모든 공연을 조회한다.")
    void findAllPerformanceTest() {
        //given, when
        Pageable pageable = Pageable.builder()
            .pageNum(1)
            .size(10)
            .build();

        List<PerformanceResponse> performances = performanceService.findPerformancesByPage(pageable);

        //then
        assertFalse(performances.isEmpty());

    }

    @Test
    @Order(4)
    @Transactional
    @DisplayName("특정 ID의 공연을 수정한다.")
    void updatePerformanceByIdTest() {
        //given
        Long performanceId = 6L;

        //when
        PerformanceIdResponse updatedPerformanceIdResponse = performanceService.updatePerformance(performanceId,
            performanceUpdateRequest);

        //then
        assertNotNull(updatedPerformanceIdResponse);

        PerformanceResponse updatedPerformance = performanceService.findPerformanceById(
            updatedPerformanceIdResponse.getPerformanceId());

        assertEquals(performanceUpdateRequest.getTitle(), updatedPerformance.getTitle());
    }

    @Test
    @Order(5)
    @Transactional
    @DisplayName("특정 ID의 공연을 삭제한다.")
    void deletePerformanceByIdTest() {
        //given
        Long performanceId = 6L;

        //when
        performanceService.deletePerformanceById(performanceId);

        //then
        assertThrows(PerformanceException.class,
            () -> performanceService.findPerformanceById(performanceId),
            PERFORMANCE_NOT_EXIST.getMessage());
    }
}

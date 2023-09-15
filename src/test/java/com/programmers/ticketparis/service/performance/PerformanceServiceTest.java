package com.programmers.ticketparis.service.performance;

import com.programmers.ticketparis.domain.performance.Category;
import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceIdResponse;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.PerformanceException;
import com.programmers.ticketparis.repository.performance.MybatisPerformanceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformanceServiceTest {

    private static PerformanceCreateRequest performanceCreateRequest;
    private static PerformanceUpdateRequest performanceUpdateRequest;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private MybatisPerformanceRepository mybatisPerformanceRepository;

    @BeforeAll
    void setUp() {
        performanceCreateRequest = new PerformanceCreateRequest(
                "제4회 더 싱어즈 정기연주회",
                "http://www.kopis.or.kr/upload/pfmPoster/PF_PF224754_230828_165705.gif",
                LocalDate.of(2023, 9, 20),
                LocalDate.of(2023, 12, 20),
                "2시간",
                15,
                15000,
                Category.MUSICAL,
                "테스트 공연 입니다.",
                1L,
                1L);

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

    @Transactional
    @Order(1)
    @Test
    @DisplayName("공연을 생성하고, 생성된 공연의 ID를 반환한다.")
    void createPerformanceTest() {
        //given & when

        PerformanceIdResponse performanceIdResponse = performanceService.createPerformance(performanceCreateRequest);

        //then
        assertNotNull(performanceIdResponse);
        assertNotNull(performanceIdResponse.getPerformanceId());
    }

    @Order(2)
    @Test
    @DisplayName("저장된 공연을 ID로 조회한다.")
    void findPerformanceByIdTest() {
        //given
        Long performanceId = 6L;

        //when
        PerformanceResponse foundPerformance = performanceService.findPerformanceById(performanceId);

        //then
        assertNotNull(foundPerformance);
    }

    @Order(3)
    @Test
    @DisplayName("저장된 모든 공연을 조회한다.")
    void findAllPerformanceTest() {
        //given & when
        List<PerformanceResponse> performances = performanceService.findAllPerformances();

        //then
        assertFalse(performances.isEmpty());

    }

    @Transactional
    @Order(4)
    @Test
    @DisplayName("특정 ID의 공연을 수정한다.")
    void updatePerformanceByIdTest() {
        //given
        Long performanceId = 6L;

        //when
        PerformanceIdResponse updatedPerformanceIdResponse = performanceService.updatePerformance(performanceId, performanceUpdateRequest);

        //then
        assertNotNull(updatedPerformanceIdResponse);
        PerformanceResponse updatedPerformance = performanceService.findPerformanceById(updatedPerformanceIdResponse.getPerformanceId());
        assertEquals(performanceUpdateRequest.getTitle(), updatedPerformance.getTitle());
    }

    @Transactional
    @Order(5)
    @Test
    @DisplayName("특정 ID의 공연을 삭제한다.")
    void deletePerformanceByIdTest() {
        //given
        Long performanceId = 6L;
        
        //when
        performanceService.deletePerformanceById(performanceId);

        //then
        assertThrows(PerformanceException.class, () -> {
            performanceService.findPerformanceById(performanceId);
        }, ExceptionRule.PERFORMANCE_NOT_EXIST.getMessage());
    }
}

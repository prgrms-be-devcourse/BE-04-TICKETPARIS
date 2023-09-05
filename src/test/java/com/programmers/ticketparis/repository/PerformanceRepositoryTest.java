package com.programmers.ticketparis.repository;

import com.programmers.ticketparis.domain.Category;
import com.programmers.ticketparis.domain.Performance;
import com.programmers.ticketparis.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.mapper.PerformanceMapper;
import com.programmers.ticketparis.service.PerformanceService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.programmers.ticketparis.domain.Category.MUSICAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerformanceRepositoryTest {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    @Transactional
    @DisplayName("💽 공연 저장 테스트 ")
    void savePerformanceTest() {

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

        //when
        performanceRepository.save(performance);

        //then
        List<Performance> savedPerformance = performanceRepository.findAll();
        assertThat(savedPerformance).hasSizeGreaterThan(0);
    }

    @Order(2)
    @DisplayName("✍️공연 업데이트 테스트 ")
    void updatePerformanceTest() {
        //given

        //when

        //then

    }

    @Order(3)
    @DisplayName("📝해당 PerformanceId의 공연 정보 조회 테스트")
    void findPerformanceByIdTest() {
        //given

        //when

        //then

    }

    @Order(4)
    @DisplayName("📋저장된 모든 공연 조회 테스트")
    void findAllPerformanceTest() {
        //given

        //when

        //then

    }

    @Order(5)
    @DisplayName("🥹공연 삭제 테스트")
    void deletePerformanceByIdTest() {
        //given

        //when

        //then
    }
}

package com.programmers.ticketparis.repository.schedule;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.schedule.Schedule;

@SpringBootTest
class MybatisScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("스케줄 데이터를 DB에 삽입 한 후 ID를 반환한다.")
    @Transactional
    void test_scheduleId_when_save_schedule() {
        //given
        Schedule schedule = Schedule.builder()
            .startDatetime(LocalDateTime.of(2023, 9, 11, 19, 30))
            .sequence(1)
            .seatsCount(598)
            .performanceId(1L)
            .build();

        //when
        Long scheduleId = scheduleRepository.save(schedule);

        //then
        assertThat(scheduleId).isEqualTo(schedule.getScheduleId());
    }

    @ParameterizedTest
    @DisplayName("공연 ID를 이용해, 공연이 진행되는 공연장의 좌석 수를 조회한다.")
    @CsvSource(value = {"1,598", "2,598", "3,200", "5,559"})
    void test_hall_seatsCount_from_performanceId(Long performanceId, Integer expectedSeatsCount) {
        //when
        Integer actualSeatsCount = scheduleRepository.findHallSeatsCountByPerformanceId(performanceId);

        //then
        assertThat(actualSeatsCount).isEqualTo(expectedSeatsCount);
    }

    @ParameterizedTest
    @DisplayName("특정 스케줄 ID에 해당하는 스케줄이 존재하는지 아닌지 판별한다.")
    @CsvSource(value = {"1,true", "2,true", "0,false", "99999999,false"})
    void test_existed_scheduleId(Long scheduleId, Boolean expectedStatus) {
        //when
        Boolean actualStatus = scheduleRepository.isExistedScheduleId(scheduleId);

        //then
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    @ParameterizedTest
    @DisplayName("특정 스케줄 ID에 해당하는 스케줄을 삭제한다.")
    @ValueSource(longs = {1, 2, 3, 4, 5})
    @Transactional
    void test_schedule_existed_or_not_after_delete_schedule(Long scheduleId) {
        //when
        scheduleRepository.deleteById(scheduleId);

        //then
        Boolean status = scheduleRepository.isExistedScheduleId(scheduleId);
        assertThat(status).isFalse();
    }
}
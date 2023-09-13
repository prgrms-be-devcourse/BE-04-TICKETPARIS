package com.programmers.ticketparis.repository.schedule;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.schedule.Schedule;

@SpringBootTest
class MybatisScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("스케줄 데이터를 정상적으로 DB에 삽입 후 스케줄 ID를 반환한다.")
    @Transactional
    void test_save_schedule_success() {
        //given
        Schedule expectedSchedule = Schedule.builder()
            .startDatetime(LocalDateTime.of(2023, 9, 11, 19, 30))
            .sequence(1)
            .seatsCount(598)
            .performanceId(1L)
            .build();

        //when
        Long scheduleId = scheduleRepository.save(expectedSchedule);

        //then
        Schedule actualSchedule = scheduleRepository.findById(scheduleId).get();
        assertThat(actualSchedule.getScheduleId()).isEqualTo(expectedSchedule.getScheduleId());
    }

    @ParameterizedTest
    @DisplayName("공연 ID를 이용해, 공연이 진행되는 공연장의 좌석 수를 조회한다.")
    @CsvSource(value = {"1,598", "2,598", "3,200", "5,559"})
    void test_find_hall_seatsCount(Long performanceId, Integer expectedSeatsCount) {
        //when
        Integer actualSeatsCount = scheduleRepository.findHallSeatsCountByPerformanceId(performanceId);

        //then
        assertThat(actualSeatsCount).isEqualTo(expectedSeatsCount);
    }

    // todo: scheduleRepository.findById() 와 scheduleRepository.existsById() 에 대한 테스트 작성
    @Test
    @DisplayName("스케줄 ID에 해당하는 스케줄을 조회한다.")
    void test_find_schedule_success() {
        //given
        Long scheduleId = 1L;

        //when
        Schedule schedule = scheduleRepository.findById(scheduleId).get();

        //then
        assertThat(schedule.getScheduleId()).isEqualTo(scheduleId);
    }

    @ParameterizedTest
    @DisplayName("존재하지 않는 스케줄 ID에 대해서는 스케줄을 조회하지 못한다.")
    @CsvSource(value = {"0", "9999999", "-100", "-1"})
    void test_find_schedule_fail(Long scheduleId) {
        //when
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);

        //then
        assertThat(schedule).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("스케줄 ID에 해당하는 스케줄의 존재 여부를 판별한다.")
    @CsvSource(value = {"1,true", "0,false", "9999999,false"})
    void test_exists_schedule(Long scheduleId, Boolean expectedStatus) {
        //when
        Boolean actualStatus = scheduleRepository.existsById(scheduleId);

        //then
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("스케줄 ID에 해당하는 스케줄을 삭제한다.")
    @Transactional
    void test_delete_schedule_success() {
        //given
        Long scheduleId = 1L;

        //when
        Integer deletedCounts = scheduleRepository.deleteById(scheduleId);

        //then
        assertThat(deletedCounts).isOne();
    }

    @ParameterizedTest
    @DisplayName("존재하지 않는 스케줄 ID에 대해서는 스케줄을 삭제하지 못한다.")
    @CsvSource(value = {"0", "9999999", "-100", "-1"})
    @Transactional
    void test_delete_schedule_fail(Long scheduleId) {
        //when
        Integer deletedCounts = scheduleRepository.deleteById(scheduleId);

        //then
        assertThat(deletedCounts).isZero();
    }
}

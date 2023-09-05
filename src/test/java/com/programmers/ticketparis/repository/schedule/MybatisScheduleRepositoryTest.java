package com.programmers.ticketparis.repository.schedule;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.ticketparis.domain.schedule.Schedule;

@SpringBootTest
class MybatisScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("스케줄 데이터를 DB에 삽입 한 후 ID를 반환한다.")
    void test_scheduleId_when_save_schedule() {
        Schedule schedule = Schedule.builder()
            .startDatetime(LocalDateTime.of(2023, 9, 11, 19, 30))
            .sequence(1)
            .seatsCount(598)
            .performanceId(1L)
            .build();

        Long scheduleId = scheduleRepository.save(schedule);
        assertThat(scheduleId).isEqualTo(schedule.getScheduleId());
    }
}
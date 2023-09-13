package com.programmers.ticketparis.service.schedule;

import static com.programmers.ticketparis.exception.ExceptionRule.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.exception.ScheduleException;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @ParameterizedTest
    @DisplayName("존재하지 않는 스케줄 ID에 대한 스케줄 삭제 시, 예외가 발생한다.")
    @CsvSource(value = {"0", "9999999", "-100", "-1"})
    @Transactional
    void test_delete_schedule_throw_exception(Long scheduleId) {
        //when, then
        assertThatThrownBy(() -> scheduleService.deleteScheduleById(scheduleId))
            .isInstanceOf(ScheduleException.class)
            .hasMessage(SCHEDULE_NOT_FOUND.getMessage());
    }
}

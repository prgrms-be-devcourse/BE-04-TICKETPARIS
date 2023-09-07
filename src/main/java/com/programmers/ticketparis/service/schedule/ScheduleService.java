package com.programmers.ticketparis.service.schedule;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.schedule.Schedule;
import com.programmers.ticketparis.dto.schedule.request.ScheduleCreateRequest;
import com.programmers.ticketparis.dto.schedule.response.ScheduleResponse;
import com.programmers.ticketparis.exception.ScheduleException;
import com.programmers.ticketparis.repository.schedule.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse createSchedule(Long performanceId, ScheduleCreateRequest request) {
        // TODO: performanceId에 해당하는 공연 데이터가 없는 경우 예외 처리 예정 (2023.09.06 김영주 작성)
        // TODO: startDatetime이 공연의 시작, 종료날짜 범위를 벗어나는 경우 예외 처리 예정 (2023.09.06 김영주 작성)

        Integer seatsCount = scheduleRepository.findHallSeatsCountByPerformanceId(performanceId);
        Schedule schedule = request.toEntity(seatsCount, performanceId);
        Long savedScheduleId = scheduleRepository.save(schedule);

        return ScheduleResponse.from(savedScheduleId);
    }

    @Transactional
    public void deleteScheduleById(Long performanceId, Long scheduleId) {
        if (scheduleRepository.existsById(performanceId, scheduleId)) {
            scheduleRepository.deleteById(performanceId, scheduleId);
            return;
        }

        List<String> rejectedValues = List.of(String.valueOf(performanceId), String.valueOf(scheduleId));

        throw new ScheduleException(SCHEDULE_NOT_FOUND, rejectedValues);
    }
}

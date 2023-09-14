package com.programmers.ticketparis.service.performance;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.pageable.Pageable;
import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceIdResponse;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
import com.programmers.ticketparis.dto.reservation.response.ReservationResponse;
import com.programmers.ticketparis.exception.ExceptionRule;
import com.programmers.ticketparis.exception.PerformanceException;
import com.programmers.ticketparis.repository.performance.PerformanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Transactional
    public PerformanceIdResponse createPerformance(PerformanceCreateRequest createRequest) {
        Performance performance = createRequest.toEntity();
        Long performanceId = performanceRepository.save(performance);

        return PerformanceIdResponse.from(performanceId);
    }

    @Transactional
    public PerformanceIdResponse updatePerformance(Long performanceId,
        PerformanceUpdateRequest performanceUpdateRequest) {
        validatePerformanceExists(performanceId);
        performanceRepository.update(performanceId, performanceUpdateRequest);

        return PerformanceIdResponse.from(performanceId);
    }

    public PerformanceResponse findPerformanceById(Long performanceId) {
        return performanceRepository.findById(performanceId)
            .map(PerformanceResponse::fromEntity)
            .orElseThrow(
                () -> new PerformanceException(ExceptionRule.PERFORMANCE_NOT_EXIST,
                    List.of(String.valueOf(performanceId))));
    }

    public List<PerformanceResponse> findPerformancesByPage(Pageable pageable) {
        return performanceRepository.findPerformancesByPage(pageable).stream()
            .map(PerformanceResponse::fromEntity)
            .toList();
    }

    public List<ReservationResponse> findReservationsByPerformanceIdWithPage(Long performanceId, Pageable pageable) {
        return performanceRepository.findReservationsByPerformanceIdWithPage(performanceId, pageable).stream()
            .map(ReservationResponse::from)
            .toList();
    }

    @Transactional
    public void deletePerformanceById(Long performanceId) {
        validatePerformanceExists(performanceId);
        performanceRepository.deleteById(performanceId);
    }

    private void validatePerformanceExists(Long performanceId) {
        if (!performanceRepository.existsById(performanceId)) {
            throw new PerformanceException(ExceptionRule.PERFORMANCE_NOT_EXIST, List.of(String.valueOf(performanceId)));
        }
    }
}

package com.programmers.ticketparis.service.performance;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
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
    public void createPerformance(PerformanceCreateRequest createRequest) {
        Performance performance = createRequest.toEntity();
        performanceRepository.save(performance);
    }

    @Transactional
    public PerformanceResponse updatePerformance(Long performanceId, PerformanceUpdateRequest updateRequest) {
        validatePerformanceExists(performanceId);
        performanceRepository.update(performanceId, updateRequest);

        return findPerformanceById(performanceId);
    }

    public PerformanceResponse findPerformanceById(Long performanceId) {

        return performanceRepository.findById(performanceId)
            .map(PerformanceResponse::fromEntity)
            .orElseThrow(
                () -> new PerformanceException(ExceptionRule.NOT_FOUND_PERFORMANCE,
                    List.of(String.valueOf(performanceId))));
    }

    public List<PerformanceResponse> findPerformanceAll() {

        return performanceRepository.findAll().stream()
            .map(performance -> PerformanceResponse.fromEntity(performance))
            .toList();
    }

    @Transactional
    public void deletePerformanceById(Long performanceId) {
        validatePerformanceExists(performanceId);
        performanceRepository.deleteById(performanceId);
    }

    private void validatePerformanceExists(Long performanceId) {
        if (!performanceRepository.existsById(performanceId)) {
            throw new PerformanceException(ExceptionRule.NOT_FOUND_PERFORMANCE, List.of(String.valueOf(performanceId)));
        }
    }
}

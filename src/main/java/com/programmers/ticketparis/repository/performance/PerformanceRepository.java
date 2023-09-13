package com.programmers.ticketparis.repository.performance;

import java.util.List;
import java.util.Optional;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;

public interface PerformanceRepository {

    Long save(Performance performance);

    Long update(Long performanceId, PerformanceUpdateRequest updateRequest);

    Optional<Performance> findById(Long performanceId);

    List<Performance> findAll();

    void deleteById(Long performanceId);

    Boolean existsById(Long performanceId);
}

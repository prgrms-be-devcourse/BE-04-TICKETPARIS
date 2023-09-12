package com.programmers.ticketparis.repository.performance;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface PerformanceRepository {

    void save(Performance performance);

    void update(Long performanceId, PerformanceUpdateRequest updateRequest);

    Optional<Performance> findById(Long performanceId);

    List<Performance> findAll();

    void deleteById(Long performanceId);

    Boolean existsById(Long performanceId);
}

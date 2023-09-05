package com.programmers.ticketparis.repository.performance;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.mapper.PerformanceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PerformanceRepository {

    private final PerformanceMapper performanceMapper;

    public void save(Performance performance) {
        performanceMapper.save(performance);
    }

    public void update(Long performanceId, PerformanceUpdateRequest updateRequest) {
        performanceMapper.update(performanceId, updateRequest);
    }

    public Optional<Performance> findById(Long id) {
        return performanceMapper.findById(id);
    }

    public List<Performance> findAll() {
        return performanceMapper.findAll();
    }

    public void deleteById(Long id) {
        performanceMapper.deleteById(id);
    }
}

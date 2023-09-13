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

public class MybatisPerformanceRepository implements PerformanceRepository {

    private final PerformanceMapper performanceMapper;

    @Override
    public Long save(Performance performance) {

        return performanceMapper.save(performance);
    }

    @Override
    public Long update(Long performanceId, PerformanceUpdateRequest performanceUpdateRequest) {

        return performanceMapper.update(performanceId, performanceUpdateRequest);
    }

    @Override
    public Optional<Performance> findById(Long performanceId) {
        return performanceMapper.findById(performanceId);
    }

    @Override
    public List<Performance> findAll() {
        return performanceMapper.findAll();
    }

    @Override
    public void deleteById(Long performanceId) {
        performanceMapper.deleteById(performanceId);
    }

    @Override
    public Boolean existsById(Long performanceId) {
        return performanceMapper.existsById(performanceId);
    }
}

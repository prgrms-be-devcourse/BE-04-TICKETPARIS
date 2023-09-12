package com.programmers.ticketparis.repository.performance;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.mapper.PerformanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor

public class MybatisPerformanceRepository implements PerformanceRepository {

    private final PerformanceMapper performanceMapper;

    @Override
    public void save(Performance performance) {
        performanceMapper.save(performance);
    }

    @Override
    public void update(Long performanceId, PerformanceUpdateRequest performanceUpdateRequest) {
        performanceMapper.update(performanceId, performanceUpdateRequest);
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

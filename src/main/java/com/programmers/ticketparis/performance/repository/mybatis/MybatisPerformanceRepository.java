package com.programmers.ticketparis.performance.repository.mybatis;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.performance.domain.Performance;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.performance.repository.PerformanceRepository;
import com.programmers.ticketparis.performance.repository.mapper.PerformanceMapper;
import com.programmers.ticketparis.reservation.domain.Reservation;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class MybatisPerformanceRepository implements PerformanceRepository {

    private final PerformanceMapper performanceMapper;

    @Override
    public Long save(Performance performance) {
        performanceMapper.save(performance);

        return performance.getPerformanceId();
    }

    @Override
    public Long update(Long performanceId, PerformanceUpdateRequest performanceUpdateRequest) {
        performanceMapper.update(performanceId, performanceUpdateRequest);

        return performanceId;
    }

    @Override
    public Optional<Performance> findById(Long performanceId) {

        return performanceMapper.findById(performanceId);
    }

    @Override
    public List<Performance> findPerformancesByPage(Pageable pageable) {
        return performanceMapper.findPerformancesByPage(pageable);
    }

    @Override
    public List<Reservation> findReservationsByPerformanceIdWithPage(Long performanceId, Pageable pageable) {
        return performanceMapper.findReservationsByPerformanceIdWithPage(performanceId, pageable);
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

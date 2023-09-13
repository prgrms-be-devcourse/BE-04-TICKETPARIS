package com.programmers.ticketparis.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;

@Mapper
public interface PerformanceMapper {

    Long save(Performance performance);

    Long update(Long performanceId, PerformanceUpdateRequest updateRequest);

    Optional<Performance> findById(Long performanceId);

    List<Performance> findAll();

    void deleteById(Long performanceId);

    Boolean existsById(Long performanceId);
}

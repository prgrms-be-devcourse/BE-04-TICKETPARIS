package com.programmers.ticketparis.mapper;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PerformanceMapper {

    void save(Performance performance);

    void update(Long performanceId, PerformanceUpdateRequest updateRequest);

    Optional<Performance> findById(Long performanceId);

    List<Performance> findAll();

    void deleteById(Long performanceId);

    Boolean existsById(Long performanceId);
}

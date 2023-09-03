package com.programmers.ticketparis.mapper;

import com.programmers.ticketparis.domain.Performance;
import com.programmers.ticketparis.dto.request.PerformanceUpdateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PerformanceMapper {

    void save(Performance performance);

    void update(@Param("id") Long performanceId, @Param("updateRequest") PerformanceUpdateRequest updateRequest);

    Optional<Performance> findById(Long id);

    List<Performance> findAll();

    void deleteById(Long id);
}

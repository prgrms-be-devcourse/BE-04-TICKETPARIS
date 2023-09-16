package com.programmers.ticketparis.performance.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.performance.domain.Performance;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.reservation.domain.Reservation;

@Mapper
public interface PerformanceMapper {

    void save(Performance performance);

    void update(Long performanceId, PerformanceUpdateRequest performanceUpdateRequest);

    Optional<Performance> findById(Long performanceId);

    List<Performance> findPerformancesByPage(Pageable pageable);

    List<Reservation> findReservationsByPerformanceIdWithPage(Long performanceId, Pageable pageable);

    void deleteById(Long performanceId);

    Boolean existsById(Long performanceId);
}

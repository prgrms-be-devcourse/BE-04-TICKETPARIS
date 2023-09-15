package com.programmers.ticketparis.repository.performance;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.domain.reservation.Reservation;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;

@Mapper
public interface PerformanceMapper {

    void save(Performance performance);

    Long update(Long performanceId, PerformanceUpdateRequest updateRequest);

    Optional<Performance> findById(Long performanceId);

    List<Performance> findPerformancesByPage(Pageable pageable);

    List<Reservation> findReservationsByPerformanceIdWithPage(Long performanceId, Pageable pageable);

    void deleteById(Long performanceId);

    Boolean existsById(Long performanceId);

}

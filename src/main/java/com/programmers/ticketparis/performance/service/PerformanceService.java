package com.programmers.ticketparis.performance.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.pageable.Pageable;
import com.programmers.ticketparis.performance.domain.Performance;
import com.programmers.ticketparis.performance.dto.request.PerformanceCreateRequest;
import com.programmers.ticketparis.performance.dto.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.performance.dto.response.PerformanceIdResponse;
import com.programmers.ticketparis.performance.dto.response.PerformanceResponse;
import com.programmers.ticketparis.performance.exception.PerformanceException;
import com.programmers.ticketparis.performance.repository.PerformanceRepository;
import com.programmers.ticketparis.reservation.dto.response.ReservationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Transactional
    public PerformanceIdResponse createPerformance(PerformanceCreateRequest performanceCreateRequest) {
        Performance performance = performanceCreateRequest.toEntity();
        Long performanceId = performanceRepository.save(performance);

        return PerformanceIdResponse.from(performanceId);
    }

    @Transactional
    public PerformanceIdResponse updatePerformance(
        Long performanceId,
        PerformanceUpdateRequest performanceUpdateRequest
    ) {
        validatePerformanceExists(performanceId);
        performanceRepository.update(performanceId, performanceUpdateRequest);

        return PerformanceIdResponse.from(performanceId);
    }

    public PerformanceResponse findPerformanceById(Long performanceId) {
        return performanceRepository.findById(performanceId)
            .map(PerformanceResponse::fromEntity)
            .orElseThrow(() -> new PerformanceException(PERFORMANCE_NOT_EXIST, performanceId));
    }

    public List<PerformanceResponse> findPerformancesByPage(Pageable pageable) {
        return performanceRepository.findPerformancesByPage(pageable)
            .stream()
            .map(PerformanceResponse::fromEntity)
            .toList();
    }

    public List<ReservationResponse> findReservationsByPerformanceIdWithPage(Long performanceId, Pageable pageable) {
        return performanceRepository.findReservationsByPerformanceIdWithPage(performanceId, pageable)
            .stream()
            .map(ReservationResponse::from)
            .toList();
    }

    @Transactional
    public void deletePerformanceById(Long performanceId) {
        validatePerformanceExists(performanceId);
        performanceRepository.deleteById(performanceId);
    }

    private void validatePerformanceExists(Long performanceId) {
        if (!performanceRepository.existsById(performanceId)) {
            throw new PerformanceException(PERFORMANCE_NOT_EXIST, performanceId);
        }
    }
}

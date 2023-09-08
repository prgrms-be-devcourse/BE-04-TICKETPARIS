package com.programmers.ticketparis.service.performance;

import com.programmers.ticketparis.domain.performance.Performance;
import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
import com.programmers.ticketparis.repository.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    @Transactional
    public void createPerformance(PerformanceCreateRequest createRequest) {
        if (createRequest.getStartDate().isAfter(createRequest.getEndDate())) {
            throw new IllegalArgumentException("공연 시작 날짜는 공연 종료 날짜 이후가 될 수 없습니다.");
        }

        Performance performance = createRequest.toEntity();
        performanceRepository.save(performance);
    }

    @Transactional
    public PerformanceResponse updatePerformance(Long id, PerformanceUpdateRequest updateRequest) {
        if (updateRequest.getStartDate().isAfter(updateRequest.getEndDate())) {
            throw new IllegalArgumentException("공연 시작 날짜는 공연 종료 날짜 이후가 될 수 없습니다.");
        }
        performanceRepository.update(id, updateRequest);
        return findPerformanceById(id)
                .orElseThrow(() -> new NoSuchElementException("수정할 공연 정보를 찾지 못했습니다."));
    }

    public Optional<PerformanceResponse> findPerformanceById(Long id) {
        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 공연 정보는 존재하지 않습니다."));

        return Optional.ofNullable(PerformanceResponse.fromEntity(performance));
    }

    public List<PerformanceResponse> findPerformanceAll() {
        List<Performance> performances = performanceRepository.findAll();
        return performances.stream()
                .map(PerformanceResponse::fromEntity)
                .collect(Collectors.toList());
    }


    @Transactional
    public void deletePerformance(Long id) {
        if (!performanceRepository.findById(id).isPresent()) {
            throw new NoSuchElementException("삭제하려는 공연을 찾지 못했습니다.");
        }
        performanceRepository.deleteById(id);
    }
}

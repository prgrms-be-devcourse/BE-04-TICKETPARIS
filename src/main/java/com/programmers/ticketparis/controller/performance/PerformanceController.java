package com.programmers.ticketparis.controller.performance;

import com.programmers.ticketparis.dto.performance.ApiResponse;
import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceIdResponse;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
import com.programmers.ticketparis.service.performance.PerformanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerformanceIdResponse createPerformance(
            @Valid @RequestBody PerformanceCreateRequest performanceCreateRequest) {

        return performanceService.createPerformance(performanceCreateRequest);
    }

    @GetMapping("/{performanceId}")
    public ApiResponse<PerformanceResponse> findPerformanceById(@PathVariable Long performanceId,
                                                                HttpServletRequest httpServletRequest) {

        return ApiResponse.of(httpServletRequest.getRequestURI(),
                performanceService.findPerformanceById(performanceId));
    }

    @GetMapping
    public ApiResponse<List<PerformanceResponse>> findAllPerformances(HttpServletRequest httpServletRequest) {

        return ApiResponse.of(httpServletRequest.getRequestURI(), performanceService.findAllPerformances());
    }

    @PatchMapping("/{performanceId}")
    public PerformanceIdResponse updatePerformance(@PathVariable Long performanceId,
                                                   @Valid @RequestBody PerformanceUpdateRequest performanceUpdateRequest) {

        return performanceService.updatePerformance(performanceId, performanceUpdateRequest);
    }

    @DeleteMapping("/{performanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerformanceById(@PathVariable Long performanceId) {
        performanceService.deletePerformanceById(performanceId);
    }
}

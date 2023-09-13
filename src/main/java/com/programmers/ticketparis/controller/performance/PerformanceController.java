package com.programmers.ticketparis.controller.performance;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.performance.ApiResponse;
import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceIdResponse;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
import com.programmers.ticketparis.service.performance.PerformanceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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

package com.programmers.ticketparis.controller.performance;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.performance.request.PerformanceCreateRequest;
import com.programmers.ticketparis.dto.performance.request.PerformanceUpdateRequest;
import com.programmers.ticketparis.dto.performance.response.PerformanceResponse;
import com.programmers.ticketparis.service.performance.PerformanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performances")
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerformance(@RequestBody PerformanceCreateRequest createRequest) {
        performanceService.createPerformance(createRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponse> findPerformanceById(@PathVariable Long id) {
        Optional<PerformanceResponse> response = performanceService.findPerformanceById(id);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<PerformanceResponse> findAllPerformance() {
        return performanceService.findPerformanceAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PerformanceResponse> updatePerformance(@PathVariable Long id,
        @RequestBody PerformanceUpdateRequest updateRequest) {
        return new ResponseEntity<>(performanceService.updatePerformance(id, updateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePerformanceById(@PathVariable Long id) {
        performanceService.deletePerformance(id);
    }

}

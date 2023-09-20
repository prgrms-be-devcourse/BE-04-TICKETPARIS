package com.programmers.ticketparis.ranking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.service.RedisRankingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
public class RankingController {

    private final RedisRankingService rankingService;

    @GetMapping
    public List<RankingResponse> findTopRankingPerformances() {
        return rankingService.findTopRankingPerformances();
    }
}

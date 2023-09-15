package com.programmers.ticketparis.controller.ranking;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.ticketparis.dto.ranking.response.RankingResponse;
import com.programmers.ticketparis.service.ranking.RankingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public List<RankingResponse> findTopRankings() {
        return rankingService.findTopRankings();
    }
}

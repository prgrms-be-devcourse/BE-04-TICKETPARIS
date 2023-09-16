package com.programmers.ticketparis.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.repository.RankingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final RankingRepository rankingRepository;

    public List<RankingResponse> findTopRankingPerformances() {
        return rankingRepository.findTopRankingPerformances();
    }
}

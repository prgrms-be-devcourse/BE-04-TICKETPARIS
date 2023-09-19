package com.programmers.ticketparis.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.service.cache.RedisRankingCache;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RedisRankingCache rankingCache;

    public List<RankingResponse> findTopRankingPerformances() {
        return rankingCache.getRankingFromCache();
    }
}

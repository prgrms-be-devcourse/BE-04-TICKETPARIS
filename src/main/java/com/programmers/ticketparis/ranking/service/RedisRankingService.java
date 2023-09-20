package com.programmers.ticketparis.ranking.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.repository.RankingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "topRankings")
public class RedisRankingService implements RankingService {

    private final RankingRepository rankingRepository;

    @Override
    @Cacheable(key = "'performances'", cacheManager = "redisCacheManager")
    public List<RankingResponse> findTopRankingPerformances() {
        return putRankingCache();
    }

    @Override
    @CachePut(key = "'performances'", cacheManager = "redisCacheManager")
    public List<RankingResponse> putRankingCache() {
        List<RankingResponse> ranking = rankingRepository.findTopRankingPerformances();
        log.info("[REDIS] {}개의 실시간 랭킹 정보 갱신", ranking.size());

        return ranking;
    }
}

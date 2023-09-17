package com.programmers.ticketparis.ranking.service;

import static com.programmers.ticketparis.ranking.service.RankingCacheKeyGenerator.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.repository.RankingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private static final int INTERVAL_HOURS = 1;
    private static final String TOP_RANKING_CACHE_ALIAS = "topRankings";

    private final RankingRepository rankingRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Cacheable(value = TOP_RANKING_CACHE_ALIAS, keyGenerator = "rankingCacheKeyGenerator")
    public List<RankingResponse> findTopRankingPerformances() {
        return rankingRepository.findTopRankingPerformances();
    }

    @Scheduled(fixedDelay = INTERVAL_HOURS, timeUnit = TimeUnit.HOURS)
    public void updateTopRankingCachePeriodically() {
        String topRankingCacheFullKey = MessageFormat.format("{0}::{1}", TOP_RANKING_CACHE_ALIAS,
            getTopRankingCacheKey());

        redisTemplate.delete(topRankingCacheFullKey);
        List<RankingResponse> topRankingPerformances = findTopRankingPerformances();

        log.info("[REDIS] {}개의 실시간 랭킹 정보 갱신", topRankingPerformances.size());
    }
}

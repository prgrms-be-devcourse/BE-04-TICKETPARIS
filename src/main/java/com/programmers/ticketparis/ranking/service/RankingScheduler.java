package com.programmers.ticketparis.ranking.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.programmers.ticketparis.ranking.service.cache.RedisRankingCache;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingScheduler {

    private final RedisRankingCache rankingCache;

    @Scheduled(cron = "0 0 0/1 * * *")
    public void refreshRankingCache() {
        rankingCache.refresh();
    }
}

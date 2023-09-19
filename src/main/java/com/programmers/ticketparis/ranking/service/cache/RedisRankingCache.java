package com.programmers.ticketparis.ranking.service.cache;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.utils.RedisUtil;
import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.repository.RankingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RedisRankingCache implements RankingCache {

    private static final String RANKING_KEY = "topRankings::performances";

    private final RankingRepository rankingRepository;
    private final RedisUtil<String, List<RankingResponse>> redisUtil;

    @Override
    public List<RankingResponse> getRankingFromCache() {
        Optional<List<RankingResponse>> ranking = redisUtil.get(RANKING_KEY);

        if (ranking.isPresent()) {
            return ranking.get();
        }

        putRanking();
        return redisUtil.get(RANKING_KEY).get();
    }

    @Override
    public void refresh() {
        redisUtil.delete(RANKING_KEY);
        putRanking();
    }

    private void putRanking() {
        List<RankingResponse> ranking = rankingRepository.findTopRankingPerformances();
        redisUtil.put(RANKING_KEY, ranking);

        log.info("[REDIS] {}개의 실시간 랭킹 정보 갱신", ranking.size());
    }
}

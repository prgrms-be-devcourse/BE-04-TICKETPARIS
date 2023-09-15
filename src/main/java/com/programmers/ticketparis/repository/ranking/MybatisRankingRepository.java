package com.programmers.ticketparis.repository.ranking;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.dto.ranking.response.RankingResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisRankingRepository implements RankingRepository {

    private final RankingMapper rankingMapper;

    @Override
    public List<RankingResponse> findTopRankings() {
        return rankingMapper.findTopRankings();
    }
}

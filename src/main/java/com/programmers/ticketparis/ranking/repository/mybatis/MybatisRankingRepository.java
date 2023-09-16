package com.programmers.ticketparis.ranking.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.repository.RankingRepository;
import com.programmers.ticketparis.ranking.repository.mapper.RankingMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisRankingRepository implements RankingRepository {

    private final RankingMapper rankingMapper;

    @Override
    public List<RankingResponse> findTopRankingPerformances() {
        return rankingMapper.findTopRankingPerformances();
    }
}

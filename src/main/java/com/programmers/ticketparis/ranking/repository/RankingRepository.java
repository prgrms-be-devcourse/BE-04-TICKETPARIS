package com.programmers.ticketparis.ranking.repository;

import java.util.List;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;

public interface RankingRepository {

    List<RankingResponse> findTopRankingPerformances();
}

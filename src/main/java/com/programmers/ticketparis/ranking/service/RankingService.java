package com.programmers.ticketparis.ranking.service;

import java.util.List;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;

public interface RankingService {

    List<RankingResponse> findTopRankingPerformances();

    List<RankingResponse> putRankingCache();
}

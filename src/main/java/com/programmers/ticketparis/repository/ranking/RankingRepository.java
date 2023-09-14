package com.programmers.ticketparis.repository.ranking;

import java.util.List;

import com.programmers.ticketparis.dto.ranking.response.RankingResponse;

public interface RankingRepository {

    List<RankingResponse> findTopRankings();
}

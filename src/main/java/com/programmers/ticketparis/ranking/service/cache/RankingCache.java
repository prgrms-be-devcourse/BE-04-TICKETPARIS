package com.programmers.ticketparis.ranking.service.cache;

import java.util.List;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;

public interface RankingCache {

    List<RankingResponse> getRankingFromCache();

    void refresh();
}

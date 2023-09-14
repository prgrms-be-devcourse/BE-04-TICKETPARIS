package com.programmers.ticketparis.repository.ranking;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.dto.ranking.response.RankingResponse;

@Mapper
public interface RankingMapper {

    List<RankingResponse> findTopRankings();
}

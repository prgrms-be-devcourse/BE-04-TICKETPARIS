package com.programmers.ticketparis.ranking.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.programmers.ticketparis.ranking.dto.response.RankingResponse;

@Mapper
public interface RankingMapper {

    List<RankingResponse> findTopRankingPerformances();
}

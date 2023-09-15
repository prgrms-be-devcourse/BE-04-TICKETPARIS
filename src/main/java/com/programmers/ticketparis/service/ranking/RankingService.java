package com.programmers.ticketparis.service.ranking;

import java.util.List;

import org.springframework.stereotype.Service;

import com.programmers.ticketparis.dto.ranking.response.RankingResponse;
import com.programmers.ticketparis.repository.ranking.RankingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    public List<RankingResponse> findTopRankings() {
        return rankingRepository.findTopRankings();
    }
}

package com.programmers.ticketparis.ranking.controller;

import com.programmers.ticketparis.common.dto.ApiResponseType;
import com.programmers.ticketparis.ranking.dto.response.RankingResponse;
import com.programmers.ticketparis.ranking.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Ranking API")
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    @Operation(
        summary = "랭킹 조회",
        description = "랭킹 조회 API")
    @ApiResponse(responseCode = "200", description = "랭킹 조회 성공", content = @Content(schema = @Schema(implementation = ApiResponseType.class)), useReturnTypeSchema = true)
    @GetMapping
    public List<RankingResponse> findTopRankingPerformances() {
        return rankingService.findTopRankingPerformances();
    }
}

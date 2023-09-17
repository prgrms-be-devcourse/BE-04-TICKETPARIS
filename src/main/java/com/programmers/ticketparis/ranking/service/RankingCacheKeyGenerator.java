package com.programmers.ticketparis.ranking.service;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class RankingCacheKeyGenerator implements KeyGenerator {

    private static final String TOP_RANKING_CACHE_KEY = "performances";

    public static String getTopRankingCacheKey() {
        return TOP_RANKING_CACHE_KEY;
    }

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return TOP_RANKING_CACHE_KEY;
    }
}

package com.programmers.ticketparis.common.utils;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisUtil<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public void put(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Optional<V> get(K key) {
        V value = redisTemplate.opsForValue().get(key);

        return Optional.ofNullable(value);
    }

    public void delete(K key) {
        redisTemplate.delete(key);
    }
}

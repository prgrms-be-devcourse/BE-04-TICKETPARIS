package com.programmers.ticketparis.auth.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.programmers.ticketparis.auth.repository.localCashSessionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionExpiryScheduler {

    private final localCashSessionRepository sessionRepository;

    @Scheduled(cron = "0 * * * * *") // 매 분마다 실행(정확히 0초가 될 때마다 실행되는 방식)
    public void expireSessions() {
        LocalDateTime now = LocalDateTime.now();
        sessionRepository.getSessionLocalCash().values().removeIf
            (
                session -> Duration.between(session.getLastAccessTime(), now).toMinutes() > 30
            );
    }
}

package com.programmers.ticketparis.auth.dto;

import java.time.LocalDateTime;

import com.programmers.ticketparis.member.enums.MemberRole;

import lombok.Getter;

@Getter
public class Session {

    private MemberRole memberRole;
    private Long memberId;
    private LocalDateTime lastAccessTime;

    private Session(MemberRole memberRole, Long memberId) {
        this.memberRole = memberRole;
        this.memberId = memberId;
        this.lastAccessTime = LocalDateTime.now();
    }

    public static Session of(MemberRole memberRole, Long memberId) {
        return new Session(memberRole, memberId);
    }

    public void updateLastAccessTime() {
        this.lastAccessTime = LocalDateTime.now();
    }
}

package com.programmers.ticketparis.auth.dto;

import com.programmers.ticketparis.member.enums.MemberRule;

import lombok.Getter;

@Getter
public class SessionValueDto {

    private MemberRule memberRule;
    private Long memberId;

    private SessionValueDto(MemberRule memberRule, Long memberId) {
        this.memberRule = memberRule;
        this.memberId = memberId;
    }

    public static SessionValueDto of(MemberRule memberRule, Long memberId) {
        return new SessionValueDto(memberRule, memberId);
    }
}

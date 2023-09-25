package com.programmers.ticketparis.auth.dto;

import com.programmers.ticketparis.member.enums.MemberRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "세션 응답")
public class SessionValueDto {

    @Schema(description = "고객, 판매자 판단")
    private MemberRole memberRule;

    @Schema(description = "고객, 판매자 ID")
    private Long memberId;

    private SessionValueDto(MemberRole memberRule, Long memberId) {
        this.memberRule = memberRule;
        this.memberId = memberId;
    }

    public static SessionValueDto of(MemberRole memberRule, Long memberId) {
        return new SessionValueDto(memberRule, memberId);
    }
}

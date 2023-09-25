package com.programmers.ticketparis.auth.dto;

import com.programmers.ticketparis.member.enums.MemberRule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "세션 응답")
public class SessionValueDto {

    @Schema(description = "고객, 판매자 판단")
    private MemberRule memberRule;

    @Schema(description = "고객, 판매자 ID")
    private Long memberId;

    private SessionValueDto(MemberRule memberRule, Long memberId) {
        this.memberRule = memberRule;
        this.memberId = memberId;
    }

    public static SessionValueDto of(MemberRule memberRule, Long memberId) {
        return new SessionValueDto(memberRule, memberId);
    }
}

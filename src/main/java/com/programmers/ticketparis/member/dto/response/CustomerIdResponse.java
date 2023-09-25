package com.programmers.ticketparis.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "from")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "고객 생성 시 응답 json data 필드값")
public class CustomerIdResponse {

    @Schema(description = "고객 ID")
    private Long customerId;
}

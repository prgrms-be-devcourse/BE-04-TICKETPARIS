package com.programmers.ticketparis.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "from")
@Schema(description = "판매자 생성 시 응답 json data 필드값")
public class SellerIdResponse {

    @Schema(description = "판매자 ID")
    private Long sellerId;
}

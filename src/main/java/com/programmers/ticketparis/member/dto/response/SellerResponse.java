package com.programmers.ticketparis.member.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.ticketparis.member.domain.Seller;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "판매자 조회 시 응답 json data 필드값")
public class SellerResponse {

    @Schema(description = "아이디")
    private Long sellerId;

    @Schema(description = "비밀번호")
    private String username;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "핸드폰 번호")
    private String phone;

    @Schema(description = "사업자 등록번호")
    private String registrationNumber;

    @Schema(description = "상호명")
    private String storeName;

    @Schema(description = "판매자 생성 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @Schema(description = "판매자 수정 날짜 및 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

    private SellerResponse(Seller seller) {
        this.sellerId = seller.getSellerId();
        this.username = seller.getUsername();
        this.name = seller.getName();
        this.email = seller.getEmail();
        this.phone = seller.getPhone();
        this.registrationNumber = seller.getRegistrationNumber();
        this.storeName = seller.getStoreName();
        this.createdDatetime = seller.getCreatedDatetime();
        this.updatedDatetime = seller.getUpdatedDatetime();
    }

    public static SellerResponse from(Seller seller) {
        return new SellerResponse(seller);
    }
}

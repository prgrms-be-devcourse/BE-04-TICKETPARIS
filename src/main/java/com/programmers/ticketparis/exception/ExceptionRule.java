package com.programmers.ticketparis.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionRule {

    RESERVATION_NOT_EXIST(HttpStatus.NOT_FOUND, "해당하는 예매를 찾을 수 없음"),
    RESERVATION_STATUS_INVALID(HttpStatus.INTERNAL_SERVER_ERROR, "올바르지 않은 예매 상태"),

    SCHEDULE_NOT_EXIST(HttpStatus.NOT_FOUND, "해당하는 스케줄을 찾을 수 없음"),
    SCHEDULE_NO_SEATS(HttpStatus.INTERNAL_SERVER_ERROR, "해당하는 스케줄의 남은 좌석 수가 없음"),
    SCHEDULE_FULL_SEATS(HttpStatus.INTERNAL_SERVER_ERROR, "해당하는 스케줄의 남은 좌석 수는 전체 좌석 수보다 많을 수 없음"),

    NOT_EXIST_RESERVATION(HttpStatus.NOT_FOUND, "요청한 예매 ID가 존재하지 않음"),
    NOT_EXIST_RESERVATION_STATUS(HttpStatus.NOT_FOUND, "존재하지 않는 예약 상태"),

    CUSTOMER_NOT_EXIST(HttpStatus.NOT_FOUND, "요청한 구매자 ID가 존재하지 않음"),
    CUSTOMER_ALREADY_CREATED(HttpStatus.BAD_REQUEST, "이미 가입된 username 또는 email로 요청"),

    SELLER_NOT_EXIST(HttpStatus.NOT_FOUND, "요청한 판매자 ID가 존재하지 않음"),
    SELLER_ALREADY_CREATED(HttpStatus.BAD_REQUEST, "이미 가입된 username 또는 email 또는 registrationNumber로 요청"),

    NOT_START_DATE_AFTER_END_DATE(HttpStatus.BAD_REQUEST, "공연 시작 날짜는 공연 종료 날짜 이후가 될 수 없음"),
    NOT_FOUND_PERFORMANCE(HttpStatus.NOT_FOUND, "해당 공연이 존재하지 않음"),
    
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "사용자 입력 유효성 검사 실패"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 URL에 해당하는 리소스를 찾을 수 없음"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP Method 요청 발생"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "기타 서버 내부 에러 발생"),
    ;

    private final HttpStatus status;
    private final String message;
}

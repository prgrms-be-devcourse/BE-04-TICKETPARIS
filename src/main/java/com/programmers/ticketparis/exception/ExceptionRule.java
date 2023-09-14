package com.programmers.ticketparis.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionRule {

    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "공연 ID와 스케줄 ID에 해당하는 스케줄이 존재하지 않아 삭제 불가"),

    NOT_EXIST_RESERVATION(HttpStatus.NOT_FOUND, "요청한 예매 ID가 존재하지 않음"),
    NOT_EXIST_RESERVATION_STATUS(HttpStatus.NOT_FOUND, "존재하지 않는 예약 상태"),

    NOT_EXIST_CUSTOMER(HttpStatus.NOT_FOUND, "요청한 회원(구매자) ID가 존재하지 않음"),
    CUSTOMER_ALREADY_CREATED(HttpStatus.BAD_REQUEST, "이미 가입된 username 또는 email로 요청"),

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

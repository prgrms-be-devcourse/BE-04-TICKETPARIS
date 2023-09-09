package com.programmers.ticketparis.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionRule {

    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "공연 ID와 스케줄 ID에 해당하는 스케줄이 존재하지 않아 삭제 불가"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "사용자 입력 유효성 검사 실패"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 URL에 해당하는 리소스를 찾을 수 없음"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP Method 요청 발생"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "기타 서버 내부 에러 발생"),
    NOT_EXIST_RESERVATION(HttpStatus.NOT_FOUND, "요청한 예매 ID가 존재하지 않음"),
    NOT_EXIST_RESERVATION_STATUS(HttpStatus.NOT_FOUND, "존재하지 않는 예약 상태"),
    ;

    private final HttpStatus status;
    private final String message;
}

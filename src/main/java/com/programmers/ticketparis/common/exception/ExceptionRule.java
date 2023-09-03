package com.programmers.ticketparis.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionRule {

	BAD_REQUEST(HttpStatus.BAD_REQUEST, "사용자 입력 유효성 검사 실패"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 URL에 해당하는 리소스를 찾을 수 없음"),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP Method 요청 발생"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "기타 서버 내부 에러 발생");

	private final HttpStatus status;
	private final String message;
}

package com.programmers.ticketparis.domain.performance;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

	MUSICAL("뮤지컬"),
	CONCERT("콘서트"),
	PLAY("연극"),
	CLASSIC_AND_DANCE("클래식/무용"),
	EXHIBITION_AND_EVENT("전시/행사"),
	CHILD_AND_FAMILY("아동/가족");

	private final String name;
}

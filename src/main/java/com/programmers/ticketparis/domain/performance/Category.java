package com.programmers.ticketparis.domain.performance;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    MUSICAL("뮤지컬"),
    CONCERT("콘서트"),
    PLAY("연극"),
    CLASSIC_AND_DANCE("클래식/무용"),
    EXHIBITION_AND_EVENT("전시/행사"),
    CHILD_AND_FAMILY("아동/가족");

    private static Map<String, Category> stringToEnum = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(Category::getName, Function.identity())));
    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Category fromString(String name) {
        Category category = stringToEnum.get(name);
        if (category == null) {
            throw new IllegalArgumentException(name + "는 허용된 카테고리가 아닙니다.");
        }
        return category;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}

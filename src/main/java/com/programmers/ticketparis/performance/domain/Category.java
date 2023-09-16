package com.programmers.ticketparis.performance.domain;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.programmers.ticketparis.performance.exception.PerformanceException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

    MUSICAL("뮤지컬"),
    CONCERT("콘서트"),
    PLAY("연극"),
    CLASSIC_AND_DANCE("클래식/무용"),
    EXHIBITION_AND_EVENT("전시/행사"),
    CHILD_AND_FAMILY("아동/가족"),
    ;

    private static final Map<String, Category> stringToEnum = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(Category::getName, Function.identity())));

    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Category fromString(String categoryName) {
        if (stringToEnum.containsKey(categoryName)) {
            return stringToEnum.get(categoryName);
        }

        throw new PerformanceException(PERFORMANCE_CATEGORY_INVALID, categoryName);
    }

    @JsonValue
    public String getName() {
        return name;
    }
}

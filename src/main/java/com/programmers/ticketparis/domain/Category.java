package com.programmers.ticketparis.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
    MUSICAL("뮤지컬"),
    CONCERT("콘서트"),
    PLAY("연극"),
    CLASSIC_AND_DANCE("클래식/무용"),
    EXHIBITION_AND_EVENT("전시/행사"),
    CHILD_AND_FAMILY("아동/가족");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @JsonCreator
    public static Category fromString(String category) {
        for (Category value : Category.values()) {
            if (value.getCategory().equalsIgnoreCase(category)) {
                return value;
            }
        }
        throw new IllegalArgumentException(category + "는 허용된 카테고리가 아닙니다.");
    }

    public String getCategory() {
        return category;
    }
}

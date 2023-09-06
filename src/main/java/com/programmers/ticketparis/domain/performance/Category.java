package com.programmers.ticketparis.domain.performance;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.MappedTypes;

@Getter
@RequiredArgsConstructor
public enum Category implements CategoryEnum {
    MUSICAL("뮤지컬"),
    CONCERT("콘서트"),
    PLAY("연극"),
    CLASSIC_AND_DANCE("클래식/무용"),
    EXHIBITION_AND_EVENT("전시/행사"),
    CHILD_AND_FAMILY("아동/가족");

    private final String name;

    @JsonCreator
    public static Category fromString(String name) {
        for (Category value : Category.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(name + "는 허용된 카테고리가 아닙니다.");
    }

    @MappedTypes(Category.class)
    public static class TypeHandler extends CategoryEnumTypeHander<Category> {
        public TypeHandler() {
            super(Category.class);
        }
    }
}

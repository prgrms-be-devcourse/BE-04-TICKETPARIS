package com.programmers.ticketparis.domain.pageable;

import static com.programmers.ticketparis.exception.ExceptionRule.*;

import java.util.List;

import com.programmers.ticketparis.exception.CommonException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pageable {

    private Integer pageNum;
    private Integer size;
    private Integer skipData;

    @Builder
    private Pageable(Integer pageNum, Integer size) {
        isNumberPositive(pageNum);
        isNumberPositive(size);
        this.skipData = (pageNum - 1) * size;
        this.pageNum = pageNum;
        this.size = size;
    }

    private void isNumberPositive(Integer number) {
        if (number <= 0) {
            throw new CommonException(NUMBER_IS_NOT_POSITIVE, List.of(String.valueOf(number)));
        }
    }
}

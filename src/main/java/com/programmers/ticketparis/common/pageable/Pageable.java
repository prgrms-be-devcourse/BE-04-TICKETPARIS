package com.programmers.ticketparis.common.pageable;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;

import com.programmers.ticketparis.common.exception.CommonException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pageable {

    private Integer pageNum;
    private Integer size;
    private Integer offset;

    @Builder
    private Pageable(Integer pageNum, Integer size) {
        validateNumberIsPositive(pageNum);
        validateNumberIsPositive(size);

        this.offset = (pageNum - 1) * size;
        this.pageNum = pageNum;
        this.size = size;
    }

    private void validateNumberIsPositive(Integer number) {
        if (number <= 0) {
            throw new CommonException(COMMON_NUMBER_IS_NOT_POSITIVE, number);
        }
    }
}

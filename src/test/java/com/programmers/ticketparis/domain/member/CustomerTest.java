package com.programmers.ticketparis.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

class CustomerTest {

    @Test
    @DisplayName("구매자를 생성할 때 비밀번호가 암호화 된다")
    void encodePassword() {
        //given
        String password = "dkssudfd12!";

        //when
        Customer customer = Customer.builder()
            .username("dksadftlsr")
            .password(password)
            .name("이현현")
            .email("abcsu@naver.com")
            .birthDate(LocalDate.parse("1997-03-27"))
            .phone("010-1111-2222")
            .address("성신여대")
            .build();

        //then
        assertThat(BCrypt.checkpw(password, BCrypt.hashpw(password, BCrypt.gensalt()))).isTrue();
    }
}
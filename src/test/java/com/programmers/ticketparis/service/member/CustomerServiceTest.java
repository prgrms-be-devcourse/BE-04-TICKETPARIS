package com.programmers.ticketparis.service.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.programmers.ticketparis.exception.CustomerException;

@SpringBootTest
@ActiveProfiles("local")
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void findById() {
        //when, then
        assertThatThrownBy(() -> customerService.findById(100L))
            .isInstanceOf(CustomerException.class)
            .hasMessageContaining("요청한 회원(구매자) ID가 존재하지 않음");
    }
}
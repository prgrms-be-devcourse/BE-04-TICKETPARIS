package com.programmers.ticketparis.member.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.ticketparis.member.exception.CustomerException;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    @DisplayName("존재하지 않는 구매자 id로 조회할 수 없다.")
    void findById() {
        //when, then
        assertThatThrownBy(() -> customerService.findCustomerById(100L))
            .isInstanceOf(CustomerException.class)
            .hasMessage(CUSTOMER_NOT_EXIST.getMessage());
    }
}
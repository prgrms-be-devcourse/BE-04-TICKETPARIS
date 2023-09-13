package com.programmers.ticketparis.service.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.ticketparis.exception.CustomerException;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    @DisplayName("존재하지 않는 구매자 id로 조회할 수 없다")
    void findById() {
        //when, then
        assertThatThrownBy(() -> customerService.findCustomerById(100L))
            .isInstanceOf(CustomerException.class)
            .hasMessage("요청한 구매자 ID가 존재하지 않음");
    }
}
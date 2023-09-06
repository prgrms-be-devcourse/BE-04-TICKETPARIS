package com.programmers.ticketparis.repository.member;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.programmers.ticketparis.domain.member.Customer;
import com.programmers.ticketparis.mapper.member.CustomerMapper;

@SpringBootTest
@ActiveProfiles("local")
class MyBatisCustomerRepositoryTest {

    @Autowired
    private MyBatisCustomerRepository myBatisCustomerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    @DisplayName("구매자 계정을 생성하여 DB에 저장할 수 있다")
    void createAccount() {
        //given
        Customer customer = Customer.builder()
            .username("이현호")
            .address("성신여대")
            .name("이현호")
            .email("charlessu")
            .phone("010-1111-2222")
            .birthDate(LocalDate.parse("1997-03-27"))
            .password("하위")
            .build();

        //when
        customerMapper.createAccount(customer);

        Customer actualCustomer = myBatisCustomerRepository.findById(3L).orElseThrow(NoSuchElementException::new);

        //then
        assertThat(actualCustomer.getUsername()).isEqualTo(customer.getUsername());
        assertThat(actualCustomer.getAddress()).isEqualTo(customer.getAddress());
        assertThat(actualCustomer.getName()).isEqualTo(customer.getName());
        assertThat(actualCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(actualCustomer.getPhone()).isEqualTo(customer.getPhone());
        assertThat(actualCustomer.getBirthDate()).isEqualTo(customer.getBirthDate());
        assertThat(actualCustomer.getPassword()).isEqualTo(customer.getPassword());
    }

    @Test
    @DisplayName("DB에서 구매자 id로 조회할 수 있다")
    void findById() {
        //when
        Customer actualCustomer = customerMapper.findById(1L).orElseThrow(NoSuchElementException::new);

        //then
        assertThat(actualCustomer.getUsername()).isEqualTo("testCustomer1");
        assertThat(actualCustomer.getAddress()).isEqualTo("경기도 성남시 분당구 정자동 100-1");
        assertThat(actualCustomer.getName()).isEqualTo("구매자1");
        assertThat(actualCustomer.getEmail()).isEqualTo("testCustomer1@ticketparis.com");
        assertThat(actualCustomer.getPhone()).isEqualTo("010-1234-5678");
        assertThat(actualCustomer.getBirthDate()).isEqualTo("1990-01-01");
        assertThat(actualCustomer.getPassword()).isEqualTo("$2b$12$76taFAFPE9ydE0ZsuWkIZexWVjLBbEIRNc509/OLI5nM9d5r3fkRG");
    }

}
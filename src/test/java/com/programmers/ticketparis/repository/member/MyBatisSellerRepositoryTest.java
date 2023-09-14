package com.programmers.ticketparis.repository.member;

import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.domain.member.Seller;

@SpringBootTest
class MyBatisSellerRepositoryTest {

    @Autowired
    private MyBatisSellerRepository myBatisSellerRepository;

    @Test
    @DisplayName("판매자 계정을 생성하여 DB에 저장할 수 있다")
    @Transactional
    void createAccountTest() {
        //given
        Seller seller = Seller.builder()
            .username("dkssudtlsr")
            .password("dkssudgktlsr1")
            .name("이현현")
            .email("charlesu@naver.com")
            .phone("010-1111-2222")
            .registrationNumber("19-03-34327")
            .storeName("우루동")
            .build();

        //when
        myBatisSellerRepository.save(seller);
        Seller actualSeller = myBatisSellerRepository.findById(2L).orElseThrow(NoSuchElementException::new);

        //then
        assertThat(actualSeller.getUsername()).isEqualTo(seller.getUsername());
        assertThat(actualSeller.getName()).isEqualTo(seller.getName());
        assertThat(actualSeller.getEmail()).isEqualTo(seller.getEmail());
        assertThat(actualSeller.getPhone()).isEqualTo(seller.getPhone());
        assertThat(actualSeller.getRegistrationNumber()).isEqualTo(seller.getRegistrationNumber());
        assertThat(actualSeller.getStoreName()).isEqualTo(seller.getStoreName());
    }

    @Test
    @DisplayName("이미 가입된 username, email로 계정을 생성할 수 없다")
    @Transactional
    void canNotCreateAccountWithDuplicateValueInUniqueColumn() {
        //given
        Seller seller = Seller.builder()
            .username("dkssudtlsr")
            .password("dkssudgktlsr1")
            .name("이현현")
            .email("charlesu@naver.com")
            .phone("010-1111-2222")
            .registrationNumber("19-03-34327")
            .storeName("우루동")
            .build();

        myBatisSellerRepository.save(seller);

        //when, then
        assertThatThrownBy(() -> myBatisSellerRepository.save(seller))
            .isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("DB에서 판매자 id로 조회할 수 있다")
    void findByIdTest() {
        //when
        Seller actualSeller = myBatisSellerRepository.findById(1L).orElseThrow(NoSuchElementException::new);

        //then
        assertThat(actualSeller.getUsername()).isEqualTo("testSeller1");
        assertThat(actualSeller.getName()).isEqualTo("판매자1");
        assertThat(actualSeller.getEmail()).isEqualTo("testSeller1@ticketparis.com");
        assertThat(actualSeller.getPhone()).isEqualTo("010-4234-1674");
        assertThat(actualSeller.getRegistrationNumber()).isEqualTo("100-12-56789");
        assertThat(actualSeller.getStoreName()).isEqualTo("주식회사 티켓파리");
    }
}
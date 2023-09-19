package com.programmers.ticketparis.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.domain.Customer;
import com.programmers.ticketparis.member.domain.Seller;
import com.programmers.ticketparis.member.dto.SessionValueDto;
import com.programmers.ticketparis.member.dto.request.LoginForm;
import com.programmers.ticketparis.member.enums.MemberRule;
import com.programmers.ticketparis.member.exception.CustomerException;
import com.programmers.ticketparis.member.exception.SellerException;
import com.programmers.ticketparis.member.repository.SessionRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final SessionRepository sessionRepository;

    public void customerLogin(LoginForm loginForm, HttpServletResponse httpServletResponse) {
        Customer customer = customerService.findCustomerByUsername(loginForm.getUsername());
        if (!customer.checkPassword(loginForm.getPassword())) {
            throw new CustomerException(ExceptionRule.LOGIN_FAILED_PASSWORD_INVALID, loginForm.getPassword());
        }

        sessionRepository.createSession(SessionValueDto.of(MemberRule.CUSTOMER, customer.getCustomerId()),
            httpServletResponse);
    }

    public void sellerLogin(LoginForm loginForm, HttpServletResponse httpServletResponse) {
        Seller seller = sellerService.findSellerByUsername(loginForm.getUsername());
        if (!seller.checkPassword(loginForm.getPassword())) {
            throw new SellerException(ExceptionRule.LOGIN_FAILED_PASSWORD_INVALID, loginForm.getPassword());
        }

        sessionRepository.createSession(SessionValueDto.of(MemberRule.SELLER, seller.getSellerId()),
            httpServletResponse);
    }

    public void logout(HttpServletRequest httpServletRequest) {
        sessionRepository.expire(httpServletRequest);
    }
}

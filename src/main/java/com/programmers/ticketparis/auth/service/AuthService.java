package com.programmers.ticketparis.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.auth.dto.LoginRequest;
import com.programmers.ticketparis.auth.dto.SessionValueDto;
import com.programmers.ticketparis.auth.repository.SessionRepository;
import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.domain.Customer;
import com.programmers.ticketparis.member.domain.Seller;
import com.programmers.ticketparis.member.enums.MemberRule;
import com.programmers.ticketparis.member.exception.CustomerException;
import com.programmers.ticketparis.member.exception.SellerException;
import com.programmers.ticketparis.member.service.CustomerService;
import com.programmers.ticketparis.member.service.SellerService;

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

    public void customerLogin(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        Customer customer = customerService.findCustomerByUsername(loginRequest.getUsername());
        if (!customer.checkPassword(loginRequest.getPassword())) {
            throw new CustomerException(ExceptionRule.LOGIN_FAILED_PASSWORD_INVALID, loginRequest.getPassword());
        }

        sessionRepository.createSession(SessionValueDto.of(MemberRule.CUSTOMER, customer.getCustomerId()),
            httpServletResponse);
    }

    public void sellerLogin(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        Seller seller = sellerService.findSellerByUsername(loginRequest.getUsername());
        if (!seller.checkPassword(loginRequest.getPassword())) {
            throw new SellerException(ExceptionRule.LOGIN_FAILED_PASSWORD_INVALID, loginRequest.getPassword());
        }

        sessionRepository.createSession(SessionValueDto.of(MemberRule.SELLER, seller.getSellerId()),
            httpServletResponse);
    }

    //세션 조회 기능 : 컨트롤러가 아닌 필터, 인터셉터에서 호출
    public SessionValueDto getSessionOrNull(HttpServletRequest httpServletRequest) {
        return sessionRepository.getSessionOrNull(httpServletRequest);
    }

    public void logout(HttpServletRequest httpServletRequest) {
        sessionRepository.expire(httpServletRequest);
    }
}

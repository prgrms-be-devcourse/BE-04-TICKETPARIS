package com.programmers.ticketparis.auth.service;

import static com.programmers.ticketparis.common.exception.ExceptionRule.*;
import static com.programmers.ticketparis.common.util.SessionConst.*;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.ticketparis.auth.dto.LoginRequest;
import com.programmers.ticketparis.auth.dto.Session;
import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.auth.repository.SessionRepository;
import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.domain.Customer;
import com.programmers.ticketparis.member.domain.Seller;
import com.programmers.ticketparis.member.enums.MemberRole;
import com.programmers.ticketparis.member.exception.CustomerException;
import com.programmers.ticketparis.member.exception.SellerException;
import com.programmers.ticketparis.member.service.CustomerService;
import com.programmers.ticketparis.member.service.SellerService;

import jakarta.servlet.http.Cookie;
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

        String sessionId = sessionRepository.createSession(Session.of(MemberRole.CUSTOMER, customer.getCustomerId()));
        setCookie(httpServletResponse, sessionId);
    }

    public void sellerLogin(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        Seller seller = sellerService.findSellerByUsername(loginRequest.getUsername());
        if (!seller.checkPassword(loginRequest.getPassword())) {
            throw new SellerException(ExceptionRule.LOGIN_FAILED_PASSWORD_INVALID, loginRequest.getPassword());
        }

        String sessionId = sessionRepository.createSession(Session.of(MemberRole.SELLER, seller.getSellerId()));
        setCookie(httpServletResponse, sessionId);
    }

    public void logout(HttpServletRequest httpServletRequest) {
        Cookie sessionId = findSessionIdCookieFromRequestOrNull(httpServletRequest)
            .orElseThrow(() -> new AuthException(LOGOUT_FAILED));

        sessionRepository.expire(sessionId.getValue());
    }

    //세션 조회 기능 : 컨트롤러가 아닌 필터, 인터셉터에서 호출
    public Session getAuthenticatedSession(HttpServletRequest httpServletRequest) {
        Cookie sessionIdCookie = findSessionIdCookieFromRequestOrNull(httpServletRequest)
            .orElseThrow(() -> new AuthException(AUTHENTICATION_FAILED));

        return sessionRepository.getSession(sessionIdCookie.getValue());
    }

    private void setCookie(HttpServletResponse httpServletResponse, String sessionId) {
        httpServletResponse.addCookie(new Cookie(SESSION_COOKIE_NAME, sessionId));
    }

    private Optional<Cookie> findSessionIdCookieFromRequestOrNull(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
            .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
            .findAny();
    }
}

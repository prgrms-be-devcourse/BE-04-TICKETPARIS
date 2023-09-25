package com.programmers.ticketparis.auth.mvc.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;

import com.programmers.ticketparis.auth.dto.Session;
import com.programmers.ticketparis.auth.service.AuthService;
import com.programmers.ticketparis.auth.util.SessionThreadLocal;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private static final String[] excludePaths = {"/api/customers", "/api/customers/login", "/api/sellers",
        "/api/sellers/login", "/api/logout"};

    private final AuthService authService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
        IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        String requestURI = httpServletRequest.getRequestURI();

        if (isLoginCheckPath(requestURI)) {
            //인증 : (세션이 존재하는지 확인하는 Boolean 반환 메서드를 만들까했지만, 매 요청마다 select 두 번 나가는건 아닌 것 같아서 한 번의 조회로 해결했음)
            Session loggedInMemberInfo = authService.getAuthenticatedSession(httpServletRequest);

            //ThreadLocal에 로그인 회원 정보 배치
            SessionThreadLocal.setSessionValueDto(loggedInMemberInfo);
        }

        chain.doFilter(request, response);
    }

    //화이트 리스트의 경우 인증 체크X
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(excludePaths, requestURI);
    }
}

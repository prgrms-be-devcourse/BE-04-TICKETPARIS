package com.programmers.ticketparis.auth.mvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.programmers.ticketparis.auth.exception.AuthException;
import com.programmers.ticketparis.auth.util.SessionThreadLocal;
import com.programmers.ticketparis.auth.util.UrlToMemberRuleMatcher;
import com.programmers.ticketparis.common.exception.ExceptionRule;
import com.programmers.ticketparis.member.enums.MemberRole;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final UrlToMemberRuleMatcher urlToMemberRuleMatcher;

    //인증은 필터에서 끝났음. 인가 처리. ThreadLocal에는 무조건 멤버 정보가 있는 상황.
    //Config에서 매쳐에 등록한 URL만 인가 검사하도록 처리, 등록 안 했으면 다 통과되도록
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return true;
        }

        String requestURI = request.getRequestURI();
        MemberRole requestMemberRole = SessionThreadLocal.getSessionValueDto().getMemberRole();
        if (!urlToMemberRuleMatcher.isMatch(requestURI, requestMemberRole)) {
            throw new AuthException(ExceptionRule.AUTHORIZATION_FAILED);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) throws Exception {
        SessionThreadLocal.clear();
    }
}

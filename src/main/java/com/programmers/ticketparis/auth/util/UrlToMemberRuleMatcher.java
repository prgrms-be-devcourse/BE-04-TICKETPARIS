package com.programmers.ticketparis.auth.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.AntPathMatcher;

import com.programmers.ticketparis.member.enums.MemberRole;

public class UrlToMemberRuleMatcher {

    private Map<String, MemberRole> requestUrlToMemberRoleMap = new ConcurrentHashMap<>();
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    //URL와 MemberRule을 등록하는 기능(Config에서 사용)
    public UrlToMemberRuleMatcher registerUrlRule(String URI, MemberRole memberRole) {
        requestUrlToMemberRoleMap.put(URI, memberRole);
        return this;
    }

    //URI와 MemberRule 이 저장소에 등록되어있는지 boolean으로 반환하는 기능(인터셉터에서 활용)
    public Boolean isMatch(String requestURI, MemberRole loggedInMemberRole) {
        for (Map.Entry<String, MemberRole> entry : requestUrlToMemberRoleMap.entrySet()) {
            if (pathMatcher.match(entry.getKey(), requestURI) && entry.getValue().equals(loggedInMemberRole)) {
                System.out.println("isMath함수 : " + loggedInMemberRole.name());
                return true;
            }
        }
        return false;
    }
}

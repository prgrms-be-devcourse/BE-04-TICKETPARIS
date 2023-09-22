package com.programmers.ticketparis.auth.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.programmers.ticketparis.member.enums.MemberRule;

public class UrlToMemberRuleMatcher {

    private Map<String, MemberRule> requestUrlToMemberRoleMap = new ConcurrentHashMap<>();

    //URL와 MemberRule을 등록하는 기능(Config에서 사용)
    public UrlToMemberRuleMatcher registerUrlRule(String URI, MemberRule memberRule) {
        requestUrlToMemberRoleMap.put(URI, memberRule);
        return this;
    }

    //URI와 MemberRule 이 저장소에 등록되어있는지 boolean으로 반환하는 기능(인터셉터에서 활용)
    public Boolean isMatch(String requestURI, MemberRule loggedInMemberRule) {
        return requestUrlToMemberRoleMap.get(requestURI).equals(loggedInMemberRule);
    }
}

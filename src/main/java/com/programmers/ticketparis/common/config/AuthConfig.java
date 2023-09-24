package com.programmers.ticketparis.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.programmers.ticketparis.auth.mvc.filter.AuthenticationFilter;
import com.programmers.ticketparis.auth.mvc.filter.ExceptionHandlerFilter;
import com.programmers.ticketparis.auth.mvc.interceptor.AuthorizationInterceptor;
import com.programmers.ticketparis.auth.service.AuthService;
import com.programmers.ticketparis.auth.util.UrlToMemberRuleMatcher;
import com.programmers.ticketparis.member.enums.MemberRole;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final AuthService authService;

    @Bean
    public FilterRegistrationBean exceptionHandlerFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ExceptionHandlerFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthenticationFilter(authService));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Bean
    public UrlToMemberRuleMatcher urlToMemberRuleMatcher() {
        return new UrlToMemberRuleMatcher()
            .registerUrlRule("/api/performances/**", MemberRole.SELLER)
            .registerUrlRule("/api/schedules/**", MemberRole.SELLER)
            .registerUrlRule("/api/reservations/**", MemberRole.SELLER);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(urlToMemberRuleMatcher()))
            .order(1)
            .addPathPatterns(
                "/api/performances/**",
                "/api/schedules/**",
                "/api/reservations/**"
            );
    }
}
package com.programmers.ticketparis.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    // private final AuthService authService;
    //
    // @Bean
    // public FilterRegistrationBean exceptionHandlerFilter() {
    //     FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    //     filterRegistrationBean.setFilter(new ExceptionHandlerFilter());
    //     filterRegistrationBean.setOrder(1);
    //     filterRegistrationBean.addUrlPatterns("/*");
    //
    //     return filterRegistrationBean;
    // }
    //
    // @Bean
    // public FilterRegistrationBean authenticationFilter() {
    //     FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    //     filterRegistrationBean.setFilter(new AuthenticationFilter(authService));
    //     filterRegistrationBean.setOrder(2);
    //     filterRegistrationBean.addUrlPatterns("/*");
    //
    //     return filterRegistrationBean;
    // }

}
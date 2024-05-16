package com.example.webshopcosmetics.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CookieFilter> cookieFilterRegistration() {
        FilterRegistrationBean<CookieFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CookieFilter());
        registration.addUrlPatterns("/*"); // Áp dụng Filter cho mọi URL
        registration.setOrder(1); // Xác định thứ tự của Filter
        return registration;
    }
}

package com.dbd.seoulcinema.global.config;

import com.dbd.seoulcinema.global.interceptor.AdminLoginCheckInterceptor;
import com.dbd.seoulcinema.global.interceptor.UserLoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginCheckInterceptor())
                .addPathPatterns("/**")
                .order(1)
                .excludePathPatterns("/home", "/", "/login", "/css/**", "*.ico", "/error",
                        "/admin/**", "/schedules", "/viewSchedulesForm", "/api/schedules",
                        "/api/auth/login", "/api/admin/**", "/assets/img/**", "/movie/**",
                        "/nonmember/login", "/api/nonmembers");

        registry.addInterceptor(new AdminLoginCheckInterceptor())
                .addPathPatterns("/admin/**", "/api/admin/**")
                .order(2)
                .excludePathPatterns("/api/admin/auth/login", "/admin/login", "/admin/home", "/api/admin", "/assets/img/**",
                        "*.jpg", "*.png");
    }
}

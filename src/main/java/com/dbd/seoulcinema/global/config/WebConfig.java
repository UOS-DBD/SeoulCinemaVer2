package com.dbd.seoulcinema.global.config;

import com.dbd.seoulcinema.global.interceptor.UserLoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/home", "/", "/login", "/css/**", "*.ico", "/error",
                        "/admin/**", "/schedules", "/viewSchedulesForm", "/api/schedules",
                        "/api/auth/login");
    }
}

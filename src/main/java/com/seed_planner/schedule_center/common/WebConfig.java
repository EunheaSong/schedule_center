package com.seed_planner.schedule_center.common;

import com.seed_planner.schedule_center.common.intercepter.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    // 토큰이 필요하지 않은 uri
    private static final String[] EXCLUDE_PATHS = {
        "/member/exist", "/member/sign-up"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(EXCLUDE_PATHS);
    }
}

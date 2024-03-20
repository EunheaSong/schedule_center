package com.seed_planner.schedule_center.common.intercepter;

import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String memberId = jwtProvider.isValidToken(token);
        request.setAttribute("memberId", memberId);
        return true;
    }
}


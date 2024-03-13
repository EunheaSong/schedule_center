package com.seed_planner.schedule_center.common.jwt;


import java.util.Map;

public interface JwtProvider {
    String issueToken(Map<String, Object> data);
    String isValidToken(String token);
}

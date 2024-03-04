package com.seed_planner.schedule_center.common.jwt;


import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtProvider {
    String issueToken(Map<String, Object> data);
    void isValidToken(String token);
    Claims getClaims(String token);
    Object getData(String token, String key);


}

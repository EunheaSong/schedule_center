package com.seed_planner.schedule_center.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProviderImpl implements JwtProvider {

    private final int ACCESS_EXPIRATION;
    private final String JWT_SECRET_KEY;

    public JwtProviderImpl(
        @Value("${jwt.secret-key}") String secretKey,
        @Value("${jwt.access-expired}") int accessExpiration
    ) {
        ACCESS_EXPIRATION = accessExpiration;
        JWT_SECRET_KEY = secretKey;
    }

    public String issueToken(Map<String, Object> data) {
        return Jwts.builder()
            .addClaims(data)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
            .signWith(getKey(JWT_SECRET_KEY))
            .compact();
    }

    public void isValidToken(String token) {
        getClaims(token).getExpiration();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getKey(JWT_SECRET_KEY))
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public Object getData(String token, String key) {
        return getClaims(token).get(key);
    }


    public static Key getKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

}

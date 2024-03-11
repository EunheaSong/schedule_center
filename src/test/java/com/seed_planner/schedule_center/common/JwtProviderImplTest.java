package com.seed_planner.schedule_center.common;

import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.common.jwt.JwtProviderImpl;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtProviderImplTest {
    private final Logger logger = LoggerFactory.getLogger(JwtProviderImplTest.class);

    @Autowired
    JwtProvider provider;

    @Value("${jwt.secret-key}")
    String secretKey;

    @Test
    @DisplayName("토큰 발급 테스트")
    void issueToken() {
        String token = provider.issueToken(Map.of("email", "test123@com"));
        logger.info("token! : " + token);
        assertFalse(token.isBlank());
    }

    @Test
    @DisplayName("토큰 검증 테스트")
    void validToken() {
        String answer = "검증 실패!";
        try {
            String fakeToken = "12334567768698faketoken333faketokenfaketoken#$!#@";
            provider.isValidToken(fakeToken);
        } catch (Exception e) {
            answer = "올바른 토큰이 아닙니다!!";
        }
        assertEquals("올바른 토큰이 아닙니다!!", answer);
    }

    @Test
    @DisplayName("토큰 유효시간 검증 테스트")
    void issueAndValidToken() throws InterruptedException {
        String answer = "검증 실패!";
        String token = Jwts.builder()
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 5000))
            .signWith(JwtProviderImpl.getKey(secretKey))
            .compact();
        Thread.sleep(6000);
        try {
            provider.isValidToken(token);
        } catch (Exception e) {
            answer = "유효한 토큰이 아닙니다!!";
        }
        assertEquals("유효한 토큰이 아닙니다!!", answer);
    }
}
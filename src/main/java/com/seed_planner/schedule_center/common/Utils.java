package com.seed_planner.schedule_center.common;

import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class Utils {
    public static String customUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 15);
    }

    public static String encrypt(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes(StandardCharsets.UTF_8));
        return Arrays.toString(md.digest());
    }

    public static String getMemberId(HttpServletRequest request) {
        return request.getAttribute("memberId").toString();
    }

    //TODO : 함수명 변경...
    public static <T> T nullCheck(T t, T n) {
        Optional<T> optionalT = Optional.ofNullable(t);
        if (optionalT.isPresent()) {
            return t;
        } else {
            return optionalT.orElse(n);
        }
    }
}

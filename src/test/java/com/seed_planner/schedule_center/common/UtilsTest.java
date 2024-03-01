package com.seed_planner.schedule_center.common;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void customUUID() {
        String str = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
        System.out.println(str);
        assertEquals(15, str.length());
    }

    @Test
    void encrypt() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String str = "password";
        md.update(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(md.digest().toString());
    }
}
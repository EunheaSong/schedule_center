package com.seed_planner.schedule_center.member.application.service;

import com.seed_planner.schedule_center.member.application.port.out.MemberInfoPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberInfoPort memberInfoPort;

    @Test
    void checkEmailDuplication() {
        assertFalse(memberInfoPort.existByEmail("test@123.com"));
    }
}
package com.seed_planner.schedule_center.common;

import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberPersistencePortTest;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestSetUp {
    @Mock
    private AuthCheckPort authCheckPort;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private static MemberPersistencePortTest memberPersistencePortTest;
    public static MemberEntity member;

    @BeforeEach
    public void setupMemberData() {
        member = new MemberEntity(
            "email@aaaa.com",
            "passsss",
            ""
        );
        memberPersistencePortTest.createMember(member);
    }

}
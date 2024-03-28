package com.seed_planner.schedule_center.common;

import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberMapper;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberPersistencePort;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@ExtendWith(MockitoExtension.class)
public class TestSetUp {
    @Mock
    private AuthCheckPort authCheckPort;
    @Mock
    private JwtProvider jwtProvider;

}
package com.seed_planner.schedule_center.common;

import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TestSetUp {
    @Mock
    private AuthCheckPort authCheckPort;
    @Mock
    private JwtProvider jwtProvider;
//        @Mock
//    private static MemberMapper memberMapper = Mockito.mock(MemberMapper.class);
//
//    @Mock
//    private static MemberPersistencePort memberPersistencePort = Mockito.mock(MemberPersistencePort.class);
//    ;
//
//    @MockBean
//    private MemberMapper memberMapper;
//
//    @MockBean
//    public MemberPersistencePort memberPersistencePort;
//
//    public static MemberDomain memberDomain;
//    public static MemberEntity memberEntity;
//
//
//    @BeforeEach
//    public void setupMemberData() {
//        memberDomain = new MemberDomain("email@aaaa.com", "passsss");
//        System.out.println("개열받노");
//        memberEntity = memberMapper.domainToEntity(memberDomain);
//        System.out.println("?");
//        memberPersistencePort.create(memberDomain);
//    }

}
package com.seed_planner.schedule_center.member.adapter.out.persistence;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberPersistencePortTest {
    @Mock
    private MemberRepositoryTest memberRepositoryTest;

    public void createMember(MemberEntity memberEntity) {
        memberRepositoryTest.save(memberEntity);
    }

}
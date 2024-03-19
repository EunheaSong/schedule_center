package com.seed_planner.schedule_center.member.adapter.out.persistence;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberPersistencePortTest {
    @Mock
    private MemberRepositoryTest memberRepositoryTest;

    public MemberEntity createMember(MemberEntity memberEntity) {
        return memberRepositoryTest.save(memberEntity);
    }

    public MemberEntity findByIdAndIsDeletedFalse(String id) {
        return memberRepositoryTest.findByIdAndIsDeletedFalse(id);
    }
}
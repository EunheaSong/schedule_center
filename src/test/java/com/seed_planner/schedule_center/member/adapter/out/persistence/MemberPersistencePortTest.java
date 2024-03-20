package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.common.TestSetUp;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberPersistencePortTest extends TestSetUp {
    @Mock
    private MemberRepositoryTest memberRepositoryTest;

    public MemberEntity findByIdAndIsDeletedFalse(String id) {
        System.out.println("들어오긴옴?");
        return memberRepositoryTest.findByIdAndIsDeletedFalse(id);
    }
}
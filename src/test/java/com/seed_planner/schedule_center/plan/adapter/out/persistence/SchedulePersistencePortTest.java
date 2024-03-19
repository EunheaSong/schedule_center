package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberPersistencePortTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
class SchedulePersistencePortTest extends TestSetUp {
    @Mock
    private ScheduleRepositoryTest scheduleRepositoryTest;
    @Mock
    private ScheduleMapperTest scheduleMapperTest;
    @Mock
    private MemberPersistencePortTest memberPersistencePortTest;

    @BeforeEach
    void setting() {

    }

    @Test
    public void create() {
//        String memberId = member.getId();
//        MemberEntity memberEntity = memberPersistencePortTest.findByIdAndIsDeletedFalse(memberId);
//        ScheduleEntity schedule = scheduleMapperTest.domainToEntity(domain, memberEntity);
//        ScheduleEntity result = scheduleRepository.save(
//            schedule
//        );
//        assertEquals(memberId, memberEntity.getId());
//        assertEquals(schedule.getId(), result.getId());
    }
}
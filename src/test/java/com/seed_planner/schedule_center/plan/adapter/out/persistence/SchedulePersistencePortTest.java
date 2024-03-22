package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestConfig.class)
@DataJpaTest
class SchedulePersistencePortTest extends TestSetUp {
    @Autowired
    private ScheduleRepository scheduleRepository;
    private static ScheduleDomain scheduleDomain;
    public static MemberDomain memberDomain;
    public static MemberEntity memberEntity;


    @BeforeEach
    public void setupMemberData() {
        memberDomain = new MemberDomain("email@aaaa.com", "passsss");
        memberEntity = new MemberEntity("email@aaaa.com", "passsss", "");
        scheduleDomain = createSchedule();
    }

    public static ScheduleDomain createSchedule() {
        String title = "뽀뇨랑 데이트";
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = LocalDateTime.now();
        String place = "엄청 맛있는 치킨집";
        return new ScheduleDomain.Builder(title, startedAt, endedAt)
                .setPlace(place)
                .build();
    }

    @Test
    @DisplayName("Schedule 생성")
    public void create() {
        System.out.println("아 왜");
        ScheduleMapperTest scheduleMapperTest = new ScheduleMapperTest();
        String memberId = memberEntity.getId();

        ScheduleEntity schedule = scheduleMapperTest.domainToEntity(scheduleDomain, memberEntity);
        ScheduleEntity result = scheduleRepository.save(schedule);

        assertEquals(memberId, memberEntity.getId());
        assertEquals(schedule.getId(), result.getId());
    }
}
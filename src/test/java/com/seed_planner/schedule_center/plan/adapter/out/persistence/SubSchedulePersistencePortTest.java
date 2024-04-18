package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestConfig.class)
@DataJpaTest
class SubSchedulePersistencePortTest extends TestSetUp {
    @Autowired
    private SubScheduleRepository subScheduleRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MemberRepository memberRepository;
    private static ScheduleEntity schedule;
    public static MemberDomain memberDomain;
    public static MemberEntity memberEntity;

    @BeforeEach
    public void init() {
        memberDomain = new MemberDomain("email@aaaa.com", "passsss");
        memberEntity = new MemberEntity("email@aaaa.com", "passsss", "");
        memberRepository.save(memberEntity);
        schedule = new ScheduleEntity(
            memberEntity,
            "제목",
            LocalDateTime.now(),
            LocalDateTime.now(),
            "장소장소",
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
        scheduleRepository.save(schedule);
    }

    private SubScheduleEntity saveSubSchedule(ScheduleEntity schedule) {
        String title = "백화점 방문";
        LocalDateTime startedAt = LocalDateTime.of(2024,01,01,10,0,0);
        LocalDateTime endedAt = LocalDateTime.of(2024,01,01,10,0,0);
        String place = "영등포 신세계 백화점";
        String memo = "밥먹기 전에 백화점에 들러, 뽀뇨에게 명품백을 선물한다.";
        return subScheduleRepository.save(
            new SubScheduleEntity(
                schedule,
                title,
                startedAt,
                endedAt,
                place,
                null,
                null,
                null,
                memo,
                null
            )
        );
    }

    @Test
    @DisplayName("schedule에 해당하는 subSchedule 조회")
    public void getSubScheduleItemListBySchedule() {
        List<SubScheduleItemRes> itemList = subScheduleRepository.findSubScheduleItemListBySchedule(schedule.getId());

        assertTrue(itemList.isEmpty());

        SubScheduleEntity subSchedule = saveSubSchedule(schedule);
        List<SubScheduleItemRes> itemList2 = subScheduleRepository.findSubScheduleItemListBySchedule(schedule.getId());

        assertEquals(1, itemList2.size());
        assertEquals(subSchedule.getTitle(), itemList2.get(0).getTitle());
    }
}
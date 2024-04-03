package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QSubScheduleEntity.subScheduleEntity;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestConfig.class)
@DataJpaTest
class SubScheduleRepositoryTest {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SubScheduleRepository subScheduleRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    private static SubScheduleEntity subSchedule;
    private static ScheduleEntity schedule;
    private static MemberEntity member;

    @BeforeEach
    public void init() {
        member = new MemberEntity("email@email.com", "password", null);
        memberRepository.save(member);
        schedule = saveSchedule();
        subSchedule = saveSubSchedule(schedule);
    }

    private ScheduleEntity saveSchedule() {
        String title = "뽀뇨랑 데이트";
        LocalDateTime startedAt = LocalDateTime.of(2024,01,01,10,0,0);
        LocalDateTime endedAt = LocalDateTime.of(2024,01,01,12,0,0);
        String place = "최고로 맛있는 매운집";
        String memo = "날씨가 좋아, 맛집인 매운음식집에 가기로 했다.";
        return scheduleRepository.save(new ScheduleEntity(
            member,
            title,
            startedAt,
            endedAt,
            place,
            null,
            null,
            null,
            memo,
            null,
            null,
            null
        ));
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
    @DisplayName("schedule에 맞는 subSchedule조회")
    public void findBySchedule() {
        List<SubScheduleItemRes> res = findSubScheduleItemListBySchedule(schedule.getId());

        assertFalse(res.isEmpty());
        assertEquals(subSchedule.getId(), res.get(0).getId());
        assertEquals(1, res.size());

        saveSubSchedule(schedule);

        List<SubScheduleItemRes> res2 = findSubScheduleItemListBySchedule(schedule.getId());

        assertFalse(res2.isEmpty());
        assertEquals(2, res2.size());
    }

    @Test
    @DisplayName("schedule에 맞는 subSchedule조회 - 조회 결과 없음")
    public void findByScheduleAndResultIsNull() {
        ScheduleEntity s = saveSchedule();
        List<SubScheduleItemRes> res = findSubScheduleItemListBySchedule(s.getId());

        assertTrue(res.isEmpty());
    }

    private List<SubScheduleItemRes> findSubScheduleItemListBySchedule(String scheduleId) {
        return queryFactory.select(
                Projections.constructor(
                    SubScheduleItemRes.class,
                    subScheduleEntity.id
                )
            ).from(subScheduleEntity)
            .innerJoin(scheduleEntity).on(scheduleEntity.id.eq(scheduleId))
            .where(subScheduleEntity.schedule.eq(scheduleEntity))
            .orderBy(subScheduleEntity.startedAt.asc())
            .fetch();
    }

}
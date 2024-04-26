package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QParticipantsEntity.participantsEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestConfig.class)
@DataJpaTest
class ParticipantsRepositoryTest extends TestSetUp {
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    private static ParticipantsEntity participants;
    private static ScheduleEntity schedule;
    private static MemberEntity member;

    @BeforeEach
    public void init() {
        member = new MemberEntity("email@ematil.com", "password", null);
        memberRepository.save(member);

        String name = "뽀뇨";
        participants = new ParticipantsEntity(member, name, null);
        participantsRepository.save(participants);

        schedule = saveSchedule(Set.of(participants));
    }

    private ScheduleEntity saveSchedule(Set<ParticipantsEntity> participantsSet) {
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
            participantsSet,
            null
            )
        );
    }

    @Test
    @DisplayName("schedule에 맞는 participants 찾기")
    public void findBySchedule() {
        String scheduleId = schedule.getId();
        List<String> idList = findParticipantsIdListByScheduleId(scheduleId);

        assertFalse(idList.isEmpty());
        assertEquals(1, idList.size());
        assertEquals(participants.getId(), idList.get(0));
    }

    @Test
    @DisplayName("schedule에 맞는 participants 찾기 - 조회 결과 없음")
    public void findByScheduleResultIsNull() {
        ScheduleEntity schedule2 = saveSchedule(Set.of(participants));
        List<String> idList = findParticipantsIdListByScheduleId(schedule2.getId());

        assertTrue(idList.isEmpty());

    }

    private List<String> findParticipantsIdListByScheduleId(String scheduleId) {
        return queryFactory.select(participantsEntity.id)
            .from(participantsEntity)
            .leftJoin(scheduleEntity).on(scheduleEntity.id.eq(scheduleId))
            .where(participantsEntity.in(scheduleEntity.participantsId))
            .fetch();
    }


}
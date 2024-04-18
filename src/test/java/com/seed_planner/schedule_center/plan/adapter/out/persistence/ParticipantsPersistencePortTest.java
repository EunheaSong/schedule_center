package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestConfig.class)
@DataJpaTest
class ParticipantsPersistencePortTest extends TestSetUp {
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MemberRepository memberRepository;
    private static ScheduleEntity schedule;
    public static MemberEntity memberEntity;

    @BeforeEach
    void init() {
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

    @Test
    void createParticipants() {
    }

    @Test
    void updateParticipants() {
    }

    @Test
    void isDeletedUpdate() {
    }

    @Test
    void getBasicInfoAllByMemberId() {
    }

    @Test
    void getAllByIdInInsDeletedFalse() {
    }

    @Test
    void getParticipantsIdListByScheduleId() {
        List<String> ids = getParticipantsIdListByScheduleId(schedule.getId());

        assertTrue(ids.isEmpty());

        ParticipantsEntity participants = participantsRepository.save(
            new ParticipantsEntity(
                memberEntity,
                "천사 뽀뇨",
                null
            )
        );
        ScheduleEntity schedule2 = scheduleRepository.save(
            new ScheduleEntity(
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
                Set.of(participants),
            null
        ));
        List<String> ids2 = getParticipantsIdListByScheduleId(schedule2.getId());

        assertEquals(participants.getId(), ids2.get(0));
    }


    public List<String> getParticipantsIdListByScheduleId(String scheduleId) {
        return participantsRepository.findParticipantsIdListByScheduleId(scheduleId);
    }


}
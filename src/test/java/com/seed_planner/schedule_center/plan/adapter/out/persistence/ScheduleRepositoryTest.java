package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItemRes;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import com.seed_planner.schedule_center.plan.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QParticipantsEntity.participantsEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QSubScheduleEntity.subScheduleEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestConfig.class)
@DataJpaTest
class ScheduleRepositoryTest extends TestSetUp {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JPAQueryFactory queryFactory;

    private static MemberEntity member;
    private static ScheduleEntity schedule;


    @BeforeEach
    public void init() {
        member = new MemberEntity("aaaa@email.com", "1234qwer", "");
        memberRepository.save(member);
        schedule = new ScheduleEntity(
                member,
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
    public void test(){
        String id = schedule.getId();
        String memberId = member.getId();
        ScheduleItemRes res = queryFactory.select(
                        Projections.constructor(
                                ScheduleItemRes.class,
                                scheduleEntity.id,
                                scheduleEntity.title,
                                scheduleEntity.createdAt,
                                scheduleEntity.modifiedAt,
                                scheduleEntity.startedAt,
                                scheduleEntity.endedAt,
                                scheduleEntity.memo,
                                scheduleEntity.place,
                                Projections.constructor(
                                        Location.class,
                                        scheduleEntity.lat,
                                        scheduleEntity.lng,
                                        scheduleEntity.address
                                ),
                                scheduleEntity.categoryId
                        )
                )
                .from(scheduleEntity)
                .innerJoin(memberEntity).on(memberEntity.id.eq(memberId))
                .where(scheduleEntity.id.eq(id)
                        .and(scheduleEntity.member.eq(memberEntity))
                        .and(scheduleEntity.isDeleted.isFalse()))
                .fetchOne();

        res.setSubScheduleItemLis(findSubScheduleItemListBySchedule());
        res.setParticipantsIdList(findParticipantsIdListBySchedule());

        assertEquals(schedule.getTitle(), res.getTitle());

    }

    private List<String> findParticipantsIdListBySchedule() {
        return queryFactory.select(participantsEntity.id)
                .from(participantsEntity)
                .leftJoin(scheduleEntity).on(participantsEntity.in(scheduleEntity.participantsId))
                .where(participantsEntity.in(scheduleEntity.participantsId))
                .fetch();
    }

    private List<SubScheduleItemRes> findSubScheduleItemListBySchedule() {
        return queryFactory.select(
                Projections.constructor(
                        SubScheduleItemRes.class,
                        subScheduleEntity.id
                )
        ).from(subScheduleEntity)
                .innerJoin(scheduleEntity).on(scheduleEntity.eq(subScheduleEntity.schedule))
                .fetch();
    }

    @Test
    public void get() {
        scheduleRepository.get(schedule.getId(), member.getId());
    }
}
package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItem;
import com.seed_planner.schedule_center.plan.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;
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
    public void getScheduleItemRes() {
        String id = schedule.getId();
        String memberId = member.getId();
        ScheduleItem res = queryFactory.select(
                Projections.constructor(
                    ScheduleItem.class,
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

//        res.setSubScheduleItemLis(findSubScheduleItemListBySchedule(schedule.getId()));
//        res.setParticipantsIdList(findParticipantsIdListBySchedule(schedule.getId()));

        assertEquals(schedule.getTitle(), res.getTitle());

    }

}
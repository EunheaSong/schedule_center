package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.plan.adapter.out.persistence.ScheduleCustomRepository;
import com.seed_planner.schedule_center.plan.adapter.out.persistence.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;

@RequiredArgsConstructor
@Repository
class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public void createSchedule(ScheduleEntity schedule, String memberId) {
        MemberEntity member = queryFactory.selectFrom(memberEntity)
                .where(memberEntity.id.eq(memberId))
                .fetchOne();
    }
}

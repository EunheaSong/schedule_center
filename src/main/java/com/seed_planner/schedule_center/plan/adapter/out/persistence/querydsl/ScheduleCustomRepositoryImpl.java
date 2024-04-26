package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItem;
import com.seed_planner.schedule_center.plan.domain.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;

@RequiredArgsConstructor
@Repository
class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public ScheduleItem getScheduleItemRes(String id, String memberId) {
        return queryFactory.select(
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
    }
}

package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItemRes;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import com.seed_planner.schedule_center.plan.domain.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QCategoryEntity.categoryEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;

@RequiredArgsConstructor
@Repository
class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public ScheduleItemRes get(String id, String memberId, List<String> participantsIdList, List<SubScheduleItemRes> subScheduleItemLis) {

        return queryFactory.select(
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
                scheduleEntity.categoryId,
                    participantsIdList,
                Projections.constructor(
                        SubScheduleItemRes.class,
                        scheduleEntity.address
                )
            )
        )
        .from(scheduleEntity)
        .innerJoin(memberEntity).on(memberEntity.id.eq(memberId))
        .innerJoin(categoryEntity).on(categoryEntity.id.eq(scheduleEntity.categoryId))
        .where(scheduleEntity.id.eq(id)
                .and(scheduleEntity.member.eq(memberEntity))
                .and(scheduleEntity.isDeleted.isFalse()))
        .fetchOne();
    }
}

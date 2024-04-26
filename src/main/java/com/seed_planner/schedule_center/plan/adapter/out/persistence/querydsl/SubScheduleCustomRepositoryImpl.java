package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import com.seed_planner.schedule_center.plan.domain.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QSubScheduleEntity.subScheduleEntity;

@RequiredArgsConstructor
@Repository
public class SubScheduleCustomRepositoryImpl implements SubScheduleCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SubScheduleItemRes> findSubScheduleItemListBySchedule(String scheduleId) {
        return jpaQueryFactory.select(
                Projections.constructor(
                    SubScheduleItemRes.class,
                    subScheduleEntity.id,
                    subScheduleEntity.title,
                    subScheduleEntity.startedAt,
                    subScheduleEntity.endedAt,
                    subScheduleEntity.place,
                    subScheduleEntity.memo,
                    subScheduleEntity.imagePath,
                    Projections.constructor(
                        Location.class,
                        subScheduleEntity.lat,
                        subScheduleEntity.lng,
                        subScheduleEntity.address
                    )
                )
            ).from(subScheduleEntity)
            .innerJoin(scheduleEntity).on(scheduleEntity.id.eq(scheduleId))
            .where(subScheduleEntity.schedule.eq(scheduleEntity))
            .orderBy(subScheduleEntity.startedAt.asc())
            .fetch();
    }
}

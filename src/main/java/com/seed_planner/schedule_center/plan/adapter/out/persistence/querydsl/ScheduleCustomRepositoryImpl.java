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
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QParticipantsEntity.participantsEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QScheduleEntity.scheduleEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QSubScheduleEntity.subScheduleEntity;

@RequiredArgsConstructor
@Repository
class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public ScheduleItemRes get(String id, String memberId) {
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

        //TODO : Service 로 이동
        res.setSubScheduleItemLis(findSubScheduleItemListBySchedule());
        res.setParticipantsIdList(findParticipantsIdListBySchedule());

        return res;
    }

    //TODO : 각각 객체에 맞는 파일로 메서드 분리
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
}

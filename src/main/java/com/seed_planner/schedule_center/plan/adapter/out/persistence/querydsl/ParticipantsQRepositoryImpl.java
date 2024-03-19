package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QParticipantsEntity.participantsEntity;


@Repository
@RequiredArgsConstructor
class ParticipantsQRepositoryImpl implements ParticipantsQRepository {
    private final JPAQueryFactory queryFactory;

    @Transactional
    public void update(ParticipantsDomain domain) {
        queryFactory.update(participantsEntity)
            .set(participantsEntity.name, domain.getName())
            .set(participantsEntity.imagePath, domain.getImagePath())
            .set(participantsEntity.modifiedAt, LocalDateTime.now())
            .where(participantsEntity.id.eq(domain.getBaseDomain().getId()))
            .execute();
    }

    @Override
    public List<ParticipantsRes> getBasicInfoByMemberId(String memberId) {
        return queryFactory.select(
                Projections.constructor(
                    ParticipantsRes.class,
                    participantsEntity.name,
                    participantsEntity.imagePath,
                    participantsEntity.id,
                    participantsEntity.modifiedAt
                )
            )
            .from(participantsEntity)
            .innerJoin(memberEntity).on(participantsEntity.member.eq(memberEntity))
            .where(memberEntity.id.eq(memberId).and(participantsEntity.isDeleted.isFalse()))
            .fetch();
    }

    @Override
    public void isDeletedUpdate(String[] idList, boolean state, String memberId) {
        queryFactory.update(participantsEntity)
            .set(participantsEntity.isDeleted, state)
            .where(participantsEntity.id.in(idList)
                .and(participantsEntity.member.eq(
                    JPAExpressions.select(memberEntity)
                        .where(memberEntity.id.eq(memberId))
                    )
                )
            )
            .execute();
    }

}
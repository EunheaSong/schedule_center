package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.querydsl.jpa.JPQLQueryFactory;
import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.seed_planner.schedule_center.schedule.adapter.out.persistence.QParticipantsEntity.participantsEntity;

@Repository
@RequiredArgsConstructor
class ParticipantsQRepositoryImpl implements ParticipantsQRepository {
    private final JPQLQueryFactory queryFactory;

    @Transactional
    public void test(ParticipantsDomain domain) {
        queryFactory.update(participantsEntity)
            .set(participantsEntity.name, domain.getName())
            .set(participantsEntity.imagePath, domain.getImagePath())
            .set(participantsEntity.modifiedAt, LocalDateTime.now())
            .where(participantsEntity.id.eq(domain.getBaseDomain().getId()))
            .execute();
    }

}
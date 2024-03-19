package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.CategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.seed_planner.schedule_center.member.adapter.out.persistence.QMemberEntity.memberEntity;
import static com.seed_planner.schedule_center.plan.adapter.out.persistence.QCategoryEntity.categoryEntity;

@RequiredArgsConstructor
@Repository
public class CategoryQRepositoryImpl implements CategoryQRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryRes> getBasicInfoByMemberId(String memberId) {
        return queryFactory.select(
            Projections.constructor(
                CategoryRes.class,
                categoryEntity.id,
                categoryEntity.name,
                categoryEntity.color
            ))
            .from(categoryEntity)
            .innerJoin(memberEntity).on(memberEntity.id.eq(memberId))
            .where(categoryEntity.member.eq(memberEntity)
                .and(categoryEntity.isDeleted.isFalse()))
            .fetch();
    }
}

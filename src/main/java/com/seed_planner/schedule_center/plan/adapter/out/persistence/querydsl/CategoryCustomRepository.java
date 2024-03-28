package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.CategoryRes;

import java.util.List;

public interface CategoryCustomRepository {
    List<CategoryRes> getBasicInfoByMemberId(String memberId);
}

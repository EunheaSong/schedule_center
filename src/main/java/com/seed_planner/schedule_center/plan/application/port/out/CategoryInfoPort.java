package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.CategoryRes;

import java.util.List;

public interface CategoryInfoPort {
    List<CategoryRes> getBasicInfoAllByMemberId(String memberId);

}

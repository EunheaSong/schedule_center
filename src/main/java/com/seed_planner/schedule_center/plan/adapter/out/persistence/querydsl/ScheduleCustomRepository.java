package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItem;

public interface ScheduleCustomRepository {
    ScheduleItem getScheduleItemRes(String id, String memberId);
}

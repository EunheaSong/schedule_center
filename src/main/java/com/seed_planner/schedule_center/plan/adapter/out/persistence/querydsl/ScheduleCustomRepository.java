package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItemRes;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;

import java.util.List;

public interface ScheduleCustomRepository {
    ScheduleItemRes getScheduleItemRes(String id, String memberId);
}

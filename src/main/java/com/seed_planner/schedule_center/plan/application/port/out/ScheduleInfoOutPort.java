package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItem;

public interface ScheduleInfoOutPort {
    ScheduleItem getScheduleItemRes(String id, String memberId);
}

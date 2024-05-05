package com.seed_planner.schedule_center.plan.application.port.in;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItemRes;

public interface ScheduleInfoInPort {
    ScheduleItemRes getScheduleDetailItem(String id, String memberId);
}

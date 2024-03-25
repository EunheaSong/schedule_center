package com.seed_planner.schedule_center.plan.application.port.in;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;

public interface ScheduleUpdateInPort {
    void create(ScheduleReq req, String memberId);
}

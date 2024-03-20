package com.seed_planner.schedule_center.plan.application.port.in;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.BasicInfoRes;

public interface BasicInfoPort {
    BasicInfoRes readParticipantsAndCategory(String memberId);
}

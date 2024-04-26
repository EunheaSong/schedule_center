package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;

import java.util.List;

public interface ParticipantsInfoPort {
    List<ParticipantsRes> getBasicInfoAllByMemberId(String memberId);
    List<String> getParticipantsIdListByScheduleId(String scheduleId);

}

package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;

import java.util.List;

public interface SubScheduleInfoOutPort {
    List<SubScheduleItemRes> getSubScheduleItemListBySchedule(String scheduleId);
}

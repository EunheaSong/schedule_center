package com.seed_planner.schedule_center.plan.adapter.in.web.dto.res;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ScheduleItemRes {
    private ScheduleItem scheduleItem;
    private List<String> participantsIdList;
    private List<SubScheduleItemRes> subScheduleItemLis;
}

package com.seed_planner.schedule_center.plan.adapter.in.web.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BasicInfoRes {
    private List<ParticipantsRes> participantsList;
    private List<CategoryRes> categoryList;
}

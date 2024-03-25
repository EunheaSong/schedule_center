package com.seed_planner.schedule_center.plan.application.service;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleUpdateInPort;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleUpdateOutPort;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleService implements ScheduleUpdateInPort {
    private final ScheduleUpdateOutPort scheduleUpdateOutPort;

    @Override
    public void create(ScheduleReq req, String memberId) {


        ScheduleDomain scheduleDomain = req.of(ScheduleReq.class);


    }
}

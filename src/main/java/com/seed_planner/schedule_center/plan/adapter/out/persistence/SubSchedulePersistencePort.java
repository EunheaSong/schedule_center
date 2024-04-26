package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import com.seed_planner.schedule_center.plan.application.port.out.SubScheduleInfoOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
class SubSchedulePersistencePort implements SubScheduleInfoOutPort {
    private final SubScheduleRepository subScheduleRepository;

    @Override
    public List<SubScheduleItemRes> getSubScheduleItemListBySchedule(String scheduleId) {
        return subScheduleRepository.findSubScheduleItemListBySchedule(scheduleId);
    }

}

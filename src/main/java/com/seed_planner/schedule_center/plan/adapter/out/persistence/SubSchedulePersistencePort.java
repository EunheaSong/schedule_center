package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SubSchedulePersistencePort {
    private final SubScheduleRepository subScheduleRepository;

    public List<SubScheduleItemRes> getSubScheduleItemListBySchedule(String scheduleId) {
        return subScheduleRepository.findSubScheduleItemListBySchedule(scheduleId);
    }

}

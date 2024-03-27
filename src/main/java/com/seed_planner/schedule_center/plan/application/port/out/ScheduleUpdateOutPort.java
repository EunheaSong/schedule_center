package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;

public interface ScheduleUpdateOutPort {
    String create(ScheduleDomain domain , MemberDomain memberDomain);
}

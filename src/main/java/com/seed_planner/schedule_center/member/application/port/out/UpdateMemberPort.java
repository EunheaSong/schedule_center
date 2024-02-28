package com.seed_planner.schedule_center.member.application.port.out;

import com.seed_planner.schedule_center.member.domain.MemberDomain;

public interface UpdateMemberPort {
    void create(MemberDomain member);
}

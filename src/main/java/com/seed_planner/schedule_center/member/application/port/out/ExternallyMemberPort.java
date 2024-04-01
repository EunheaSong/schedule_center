package com.seed_planner.schedule_center.member.application.port.out;

import com.seed_planner.schedule_center.member.domain.MemberDomain;

public interface ExternallyMemberPort {
    MemberDomain getActivationMember(String id);

}

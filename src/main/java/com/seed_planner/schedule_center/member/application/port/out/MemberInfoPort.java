package com.seed_planner.schedule_center.member.application.port.out;

import com.seed_planner.schedule_center.member.domain.MemberDomain;

public interface MemberInfoPort {
    boolean existsByEmailAndIsDeleted(String email, boolean isDeleted);

    MemberDomain getByEmailAndIsDeleted(String email, boolean isDeleted);

}

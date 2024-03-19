package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberMapper;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;

class ScheduleMapperTest {
    private MemberMapper memberMapper;

    ScheduleEntity domainToEntity(ScheduleDomain domain, MemberEntity memberEntity) {
        return new ScheduleEntity(
            memberEntity,
            domain.getTitle(),
            domain.getStartedAt(),
            domain.getEndedAt(),
            domain.getPlace(),
            domain.getLocation().getLat(),
            domain.getLocation().getLng(),
            domain.getLocation().getAddress(),
            domain.getMemo().getContents(),
            domain.getImagePath(),
            domain.toStringParticipantsId(),
            domain.getCategoryId()
        );
    }
}

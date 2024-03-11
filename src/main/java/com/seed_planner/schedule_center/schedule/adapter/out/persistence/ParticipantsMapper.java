package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseMapper;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberMapper;
import com.seed_planner.schedule_center.schedule.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ParticipantsMapper {
    private final MemberMapper memberMapper;

    ParticipantsEntity domainToEntity(ParticipantsDomain domain) {
        System.out.println(domain.getName());
        return new ParticipantsEntity(
            domain.getName(),
            domain.getImagePath()
        );
    }
}

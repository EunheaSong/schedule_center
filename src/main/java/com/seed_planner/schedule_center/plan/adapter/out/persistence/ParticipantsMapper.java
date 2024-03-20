package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ParticipantsMapper {

    ParticipantsEntity domainToEntity(ParticipantsDomain domain) {
        return new ParticipantsEntity(
            domain.getName(),
            domain.getImagePath()
        );
    }
}

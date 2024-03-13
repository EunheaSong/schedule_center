package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import com.seed_planner.schedule_center.schedule.domain.ParticipantsDomain;
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

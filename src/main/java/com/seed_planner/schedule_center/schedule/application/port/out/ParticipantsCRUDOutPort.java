package com.seed_planner.schedule_center.schedule.application.port.out;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.schedule.domain.ParticipantsDomain;

import java.util.List;

public interface ParticipantsCRUDOutPort {
    List<ParticipantsRes> createParticipants(List<ParticipantsDomain> domainList, String memberId);
    List<ParticipantsRes> updateParticipants(List<ParticipantsDomain> domainList, String memberId);
}

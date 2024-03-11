package com.seed_planner.schedule_center.schedule.application.port.out;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipnatsRes;
import com.seed_planner.schedule_center.schedule.domain.ParticipantsDomain;

import java.util.List;

public interface ParticipantsCRUDOutPort {
    List<ParticipnatsRes> createParticipants(List<ParticipantsDomain> domainList, String memberId);
}

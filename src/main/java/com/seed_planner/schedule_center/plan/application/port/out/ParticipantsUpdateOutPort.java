package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;

import java.util.List;

public interface ParticipantsUpdateOutPort {
    List<ParticipantsRes> createParticipants(List<ParticipantsDomain> domainList, String memberId);
    List<ParticipantsRes> updateParticipants(List<ParticipantsDomain> domainList, String memberId);
    void isDeletedUpdate(String[] idList, boolean state, String memberId);
}

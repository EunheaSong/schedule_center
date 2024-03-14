package com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;

import java.util.List;

public interface ParticipantsQRepository {
    void update(ParticipantsDomain domain);
    List<ParticipantsRes> getBasicInfoByMemberId(String memberId);
    void isDeletedUpdate(String[] idList, boolean state, String memberId);
}

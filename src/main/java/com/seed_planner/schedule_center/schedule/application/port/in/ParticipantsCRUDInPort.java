package com.seed_planner.schedule_center.schedule.application.port.in;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipantsRes;

import java.util.List;

public interface ParticipantsCRUDInPort {

    List<ParticipantsRes> createParticipants(List<ParticipantsReq> req, String memberId);
}

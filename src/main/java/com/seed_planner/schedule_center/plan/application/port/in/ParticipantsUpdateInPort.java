package com.seed_planner.schedule_center.plan.application.port.in;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsUpdateReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;

import java.util.List;

public interface ParticipantsUpdateInPort {

    List<ParticipantsRes> createParticipants(List<ParticipantsReq> req, String memberId);
    List<ParticipantsRes> updateParticipants(List<ParticipantsUpdateReq> req, String memberId);

    /**
     * 참가자 삭제
     * 참가자를 삭제해도 현재 시점을 기준으로 이전의 일정에 등록된 참가자는 정보는 지워지지 않습니다.
     * @param req, memberId
     * @return String[] 삭제된 참가자 id
     */
    String[] deleteParticipants(String[] req, String memberId);
}

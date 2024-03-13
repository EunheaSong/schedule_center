package com.seed_planner.schedule_center.plan.application.service;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsUpdateReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.plan.application.port.in.ParticipantsCRUDInPort;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsCRUDOutPort;
import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
class ParticipantsService implements ParticipantsCRUDInPort {
    private final ParticipantsCRUDOutPort crudOutPort;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ParticipantsRes> createParticipants(List<ParticipantsReq> req, String memberId) {
        List<ParticipantsDomain> participantsDomainsList = req.stream()
            .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath()))
            .toList();
        //TODO : S3 upload - 비동기
        return crudOutPort.createParticipants(participantsDomainsList, memberId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ParticipantsRes> updateParticipants(List<ParticipantsUpdateReq> req, String memberId) {
        //TODO : S3 upload - 비동기
        List<ParticipantsDomain> participantsDomainsList = req.stream()
            .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath(), it.getId()))
            .toList();
        return crudOutPort.updateParticipants(participantsDomainsList, memberId);
    }
}

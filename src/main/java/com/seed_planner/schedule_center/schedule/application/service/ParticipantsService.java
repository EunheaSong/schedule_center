package com.seed_planner.schedule_center.schedule.application.service;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.schedule.application.port.in.ParticipantsCRUDInPort;
import com.seed_planner.schedule_center.schedule.application.port.out.ParticipantsCRUDOutPort;
import com.seed_planner.schedule_center.schedule.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class ParticipantsService implements ParticipantsCRUDInPort {
    private final ParticipantsCRUDOutPort crudOutPort;

    @Transactional
    @Override
    public List<ParticipantsRes> createParticipants(List<ParticipantsReq> req, String memberId) {
        List<ParticipantsDomain> participantsDomainsList = req.stream()
            .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath()))
            .collect(Collectors.toList());
        //TODO : S3 upload - 비동기
        return crudOutPort.createParticipants(participantsDomainsList, memberId);
    }
}

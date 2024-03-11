package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipnatsRes;
import com.seed_planner.schedule_center.schedule.application.port.out.ParticipantsCRUDOutPort;
import com.seed_planner.schedule_center.schedule.domain.ParticipantsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ParticipantsPersistencePort implements ParticipantsCRUDOutPort {
    private final ParticipantsRepository participantsRepository;
    private final ParticipantsMapper participantsMapper;
    private final BatchRepository batchRepository;

    @Override
    public List<ParticipnatsRes> createParticipants(List<ParticipantsDomain> domainList, String memberId) {
        List<ParticipantsEntity> entityList = domainList.stream().map(participantsMapper::domainToEntity).toList();
        batchRepository.participantsSaveAll(
            entityList,
            memberId
        );
        return entityList.stream()
            .map( it ->
                new ParticipnatsRes(it.getName(), it.getImagePath(), it.getId(), it.getCreatedAt(), it.getModifiedAt())
            ).toList();
    }
}

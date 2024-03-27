package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsInfoPort;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsUpdateOutPort;
import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ParticipantsPersistencePort implements ParticipantsUpdateOutPort, ParticipantsInfoPort {
    private final ParticipantsRepository participantsRepository;
    private final ParticipantsMapper participantsMapper;
    private final BatchRepository batchRepository;

    @Override
    public List<ParticipantsRes> createParticipants(List<ParticipantsDomain> domainList, String memberId) {
        List<ParticipantsEntity> entityList = domainList.stream().map(participantsMapper::domainToEntity).toList();
        batchRepository.participantsSaveAll(entityList, memberId);
        return entityList.stream()
            .map( it ->
                new ParticipantsRes(it.getName(), it.getImagePath(), it.getId(), it.getCreatedAt())
            ).toList();
    }

    @Override
    public List<ParticipantsRes> updateParticipants(List<ParticipantsDomain> domainList, String memberId) {
        List<ParticipantsRes> res = new ArrayList<>(domainList.size());
        //TODO : [Refactoring] 해당 방식대로 업데이트시 모바일, pc 에서의 동시성 문제 고려.
        for (ParticipantsDomain domain : domainList) {
            participantsRepository.update(domain);
            res.add(new ParticipantsRes(domain));
        }
        return res;
    }

    @Override
    public void isDeletedUpdate(String[] idList, boolean state, String memberId) {
        participantsRepository.isDeletedUpdate(idList, state, memberId);
    }


    @Override
    public List<ParticipantsRes> getBasicInfoAllByMemberId(String memberId) {
        return participantsRepository.getBasicInfoByMemberId(memberId);
    }

    Set<ParticipantsEntity> getAllByIdInInsDeletedFalse(List<String> idList) {
        return participantsRepository.findAllByIdInAndIsDeletedFalse(idList);
    }
}

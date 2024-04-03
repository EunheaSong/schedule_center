package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberMapper;
import com.seed_planner.schedule_center.member.application.port.out.ExternallyMemberPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleUpdateOutPort;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
class SchedulePersistencePort implements ScheduleUpdateOutPort {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final MemberMapper memberMapper;
    private final ParticipantsPersistencePort participantsPersistencePort;


    @Override
    public String create(ScheduleDomain domain, MemberDomain memberDomain) {
        //TODO : select 줄일 수 있도록 refactoring
        MemberEntity member = memberMapper.domainToEntity(memberDomain);
        Set<ParticipantsEntity> participantsSet = new HashSet<>();
        if (domain.getParticipantsId() != null && !domain.getParticipantsId().isEmpty()) {
            participantsSet = participantsPersistencePort.getAllByIdInIsDeletedFalse(domain.getParticipantsId());
        }
        return scheduleRepository
                .save(scheduleMapper.domainToEntity(domain, member, participantsSet))
                .getId();
    }
}

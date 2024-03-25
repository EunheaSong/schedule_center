package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberMapper;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleUpdateOutPort;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;
import java.util.Set;

@RequiredArgsConstructor
@Component
class SchedulePersistencePort implements ScheduleUpdateOutPort {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final MemberMapper memberMapper;

    @Override
    public void create(ScheduleDomain domain , String memberId) {
        //TODO : insert 줄일 수 있도록 refactoring
        MemberEntity member = ;
        Set<ParticipantsEntity> participantsSet = ;
        scheduleRepository.save(
                scheduleMapper.domainToEntity(domain, member)
        );
    }
}

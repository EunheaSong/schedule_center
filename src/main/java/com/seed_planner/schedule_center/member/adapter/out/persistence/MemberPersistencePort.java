package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.member.application.port.out.MemberInfoPort;
import com.seed_planner.schedule_center.member.application.port.out.UpdateMemberPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class MemberPersistencePort implements MemberInfoPort, UpdateMemberPort {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(MemberDomain member) {
        memberRepository.save(memberMapper.domainToEntity(member));
    }
}

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
    public boolean existsByEmailAndIsDeleted(String email, boolean isDeleted) {
        return memberRepository.existsByEmailAndIsDeleted(email, isDeleted);
    }

    @Override
    public MemberDomain getByEmailAndIsDeleted(String email, boolean isDeleted) {
        return memberMapper.entityToDomain(memberRepository.findByEmailAndIsDeleted(email, isDeleted));
    }

    @Override
    public MemberDomain getByIdAndIsDeletedFalse(String id) {
        return memberMapper.entityToDomain(memberRepository.findByIdAndIsDeletedFalse(id));
    }

    @Override
    public void create(MemberDomain member) {
        memberRepository.save(memberMapper.domainToEntity(member));
    }
}

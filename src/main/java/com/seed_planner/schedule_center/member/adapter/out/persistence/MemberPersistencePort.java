package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.common.exceptionHandler.ExceptionCode;
import com.seed_planner.schedule_center.common.exceptionHandler.ExceptionResponse;
import com.seed_planner.schedule_center.common.exceptionHandler.customException.BadRequestException;
import com.seed_planner.schedule_center.member.application.port.out.ExternallyMemberPort;
import com.seed_planner.schedule_center.member.application.port.out.MemberInfoPort;
import com.seed_planner.schedule_center.member.application.port.out.UpdateMemberPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class MemberPersistencePort implements MemberInfoPort, UpdateMemberPort, ExternallyMemberPort {
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

    @Override
    public MemberDomain getActivationMember(String id) {
        MemberEntity member = memberRepository.findByIdAndIsDeletedFalse(id);
        if (member == null) throw new BadRequestException(new ExceptionResponse(ExceptionCode.E10002));
        return memberMapper.entityToDomain(member);
    }

}

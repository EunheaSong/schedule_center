package com.seed_planner.schedule_center.member.application.service;

import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignUpReq;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import com.seed_planner.schedule_center.member.application.port.out.MemberInfoPort;
import com.seed_planner.schedule_center.member.application.port.out.UpdateMemberPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MemberService implements AuthCheckPort {
    private final MemberInfoPort memberInfoPort;
    private final UpdateMemberPort updateMemberPort;

    @Override
    public boolean existByEmail(String email) {
        return !memberInfoPort.existByEmail(email);
    }

    @Override
    public void memberSignUp(MemberSignUpReq req) {
//        if (!req.getPassword().equals(req.getRePassword())) throw
        updateMemberPort.create(
            new MemberDomain(req.getEmail(), req.getPassword())
        );
    }

}

package com.seed_planner.schedule_center.member.application.service;

import com.seed_planner.schedule_center.common.exceptionHandler.CustomResponse;
import com.seed_planner.schedule_center.common.exceptionHandler.customException.BadRequestException;
import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignInReq;
import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignUpReq;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import com.seed_planner.schedule_center.member.application.port.out.MemberInfoPort;
import com.seed_planner.schedule_center.member.application.port.out.UpdateMemberPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.seed_planner.schedule_center.common.exceptionHandler.ExceptionCode.E10000;
import static com.seed_planner.schedule_center.common.exceptionHandler.ExceptionCode.E10001;

@Service
@RequiredArgsConstructor
class MemberService implements AuthCheckPort {
    private final MemberInfoPort memberInfoPort;
    private final UpdateMemberPort updateMemberPort;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    @Override
    public boolean existByEmail(String email) {
        return !memberInfoPort.existsByEmailAndIsDeleted(email, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void memberSignUp(MemberSignUpReq req) {
        if (!req.getPassword().equals(req.getRePassword()))
            throw new BadRequestException(new CustomResponse(E10000));
        updateMemberPort.create(new MemberDomain(req.getEmail(), req.getPassword()));
    }

    @Override
    public String memberSignIn(MemberSignInReq req, HttpServletResponse response) {
        MemberDomain member = memberInfoPort.getByEmailAndIsDeleted(req.getEmail(), false);
        if (member == null) {
            throw new BadRequestException(new CustomResponse(E10001));
        }
        if (!req.getPassword().equals(member.getPassword())) {
            throw new BadRequestException(new CustomResponse(E10000));
        }
        return jwtProvider.issueToken(Map.of("id", member.getBaseDomain().getId()));
    }

}

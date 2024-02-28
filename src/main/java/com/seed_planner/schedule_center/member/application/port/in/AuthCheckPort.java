package com.seed_planner.schedule_center.member.application.port.in;

import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignUpReq;

public interface AuthCheckPort {

    boolean existByEmail(String email);
    void memberSignUp(MemberSignUpReq req);
}

package com.seed_planner.schedule_center.member.domain;

import com.seed_planner.schedule_center.common.model.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDomain extends BaseDomain {
    private String email;

    private String password;

    private String kakaoId;

    public MemberDomain(String email, String password) {
        super();
        this.email = email;
        this.password = password;
        this.kakaoId = null;
    }
}

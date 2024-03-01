package com.seed_planner.schedule_center.member.domain;

import com.seed_planner.schedule_center.common.model.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDomain {
    private String email;

    private String password;

    private String kakaoId;

    private BaseDomain baseDomain;

    public MemberDomain(String email, String password) {
        this.email = email;
        this.password = password;
        this.kakaoId = null;
        this.baseDomain = null;
    }

    public MemberDomain(
        String email,
        String password,
        String kakaoId,
        BaseDomain baseDomain
    ) {
        this.email = email;
        this.password = password;
        this.kakaoId = kakaoId;
        this.baseDomain = baseDomain;
    }
}

package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.member.domain.MemberDomain;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberEntity domainToEntity(MemberDomain memberDomain) {
        return new MemberEntity(
            memberDomain.getEmail(),
            memberDomain.getPassword(),
            memberDomain.getKakaoId()
        );
    }
}

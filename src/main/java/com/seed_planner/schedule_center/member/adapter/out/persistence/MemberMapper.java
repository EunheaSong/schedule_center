package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseMapper;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberMapper {
    private final BaseMapper baseMapper;

    public MemberEntity domainToEntity(MemberDomain domain) {
        return new MemberEntity(
            domain.getEmail(),
            domain.getPassword(),
            domain.getKakaoId()
        );
    }

    public MemberDomain entityToDomain(MemberEntity entity) {
        return new MemberDomain(
            entity.getEmail(),
            entity.getPassword(),
            entity.getKakaoId(),
            baseMapper.toDomain(entity)
        );
    }
}

package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseMapper;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberMapper {
    private final BaseMapper baseMapper;

    //TODO : 더 괜찮은 메서드명...
    public MemberEntity domainToInstanceOfEntity(MemberDomain domain) {
        return MemberEntity.createMember(
            domain.getEmail(),
            domain.getPassword(),
            domain.getKakaoId()
        );
    }

    public MemberEntity domainToEntity(MemberDomain domain) {
        return MemberEntity.toMemberEntity(
                domain.getEmail(),
                domain.getPassword(),
                domain.getKakaoId(),
                domain.getBaseDomain()
        );
    }

    public MemberDomain entityToInstanceOfDomain(MemberEntity entity) {
        return new MemberDomain(
            entity.getEmail(),
            entity.getPassword(),
            entity.getKakaoId(),
            baseMapper.toDomain(entity)
        );
    }
}

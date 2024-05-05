package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseDomain;
import com.seed_planner.schedule_center.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class MemberEntity extends BaseEntity {
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 15)
    private String password;

    @Column
    private String kakaoId;

    public MemberEntity(String email, String password, String kakaoId) {
        super();
        this.email = email;
        this.password = password;
        this.kakaoId = kakaoId;
    }

    private MemberEntity(String email, String password, String kakaoId, BaseDomain baseDomain) {
        super(baseDomain.getId(), baseDomain.getCreatedAt(), baseDomain.getModifiedAt(), baseDomain.isDeleted());
        this.email = email;
        this.password = password;
        this.kakaoId = kakaoId;
    }

    public static MemberEntity createMember(String email, String password, String kakaoId) {
        return new MemberEntity(email, password, kakaoId);
    }

    public static MemberEntity toMemberEntity(String email, String password, String kakaoId, BaseDomain baseDomain) {
        return new MemberEntity(email, password, kakaoId, baseDomain);
    }
}

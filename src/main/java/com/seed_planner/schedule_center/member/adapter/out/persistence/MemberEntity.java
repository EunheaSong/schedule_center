package com.seed_planner.schedule_center.member.adapter.out.persistence;

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
}

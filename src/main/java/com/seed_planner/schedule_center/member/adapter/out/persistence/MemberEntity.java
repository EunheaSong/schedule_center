package com.seed_planner.schedule_center.member.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class MemberEntity extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 15)
    private String password;

    @Column
    private String kakaoId;
}

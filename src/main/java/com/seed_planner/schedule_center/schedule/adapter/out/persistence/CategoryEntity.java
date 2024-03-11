package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
class CategoryEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity userId;
    private String name;
    private String color;
}

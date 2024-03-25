package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
class ParticipantsEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;
    @Column(length = 30)
    private String name;
    @Column
    private String imagePath;
    @ManyToMany
    private Set<ScheduleEntity> schedule;

    ParticipantsEntity(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
}

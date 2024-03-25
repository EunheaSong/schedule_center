package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
class ScheduleEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false)
    private LocalDateTime startedAt;
    @Column(nullable = false)
    private LocalDateTime endedAt;
    @Column(length = 50)
    private String place;
    @Column
    private Double lat;
    @Column
    private Double lng;
    @Column
    private String address;
    @Column(length = 200)
    private String memo;
    @Column
    private String imagePath;
    @JoinTable(
            name = "schedule_participants",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "participants_id")
    )
    @ManyToMany
    private Set<ParticipantsEntity> participantsId = new HashSet<>();;
    @Column
    private String categoryId;

}

package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class SubScheduleEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private ScheduleEntity scheduleId;
    @Column(nullable = false, length = 50)
    private String name;
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
}

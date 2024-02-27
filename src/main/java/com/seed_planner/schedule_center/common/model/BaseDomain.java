package com.seed_planner.schedule_center.common.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class BaseDomain {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean isDeleted;
}

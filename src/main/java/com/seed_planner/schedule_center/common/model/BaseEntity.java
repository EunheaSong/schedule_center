package com.seed_planner.schedule_center.common.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.seed_planner.schedule_center.common.Utils.customUUID;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @Id @Column(length = 15)
    private String id = customUUID();

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column(nullable = false)
    private boolean isDeleted = false;

    public BaseEntity(){}

    protected BaseEntity(String id, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean isDeleted) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
    }
}

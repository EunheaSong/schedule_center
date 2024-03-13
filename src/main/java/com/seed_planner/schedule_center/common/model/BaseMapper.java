package com.seed_planner.schedule_center.common.model;


import org.springframework.stereotype.Component;

@Component
public class BaseMapper {

    public BaseDomain toDomain(BaseEntity baseEntity) {
        return new BaseDomain(
            baseEntity.getId(),
            baseEntity.getCreatedAt(),
            baseEntity.getModifiedAt(),
            baseEntity.isDeleted()
        );
    }


}

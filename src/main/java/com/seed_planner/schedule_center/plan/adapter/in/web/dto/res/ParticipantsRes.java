package com.seed_planner.schedule_center.plan.adapter.in.web.dto.res;

import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParticipantsRes {
    private String name;
    private String imagePath;
    private String id;
    private LocalDateTime modifiedAt;

    public ParticipantsRes (ParticipantsDomain domain) {
        this.name = domain.getName();
        this.imagePath = domain.getImagePath();
        this.id = domain.getBaseDomain().getId();
        this.modifiedAt = domain.getBaseDomain().getModifiedAt();
    }
}

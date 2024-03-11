package com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res;

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
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

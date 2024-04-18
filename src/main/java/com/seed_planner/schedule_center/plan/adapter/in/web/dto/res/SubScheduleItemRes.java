package com.seed_planner.schedule_center.plan.adapter.in.web.dto.res;

import com.seed_planner.schedule_center.plan.domain.Location;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class SubScheduleItemRes {
    private String id;
    private String title;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String place;
    private String memo;
    private String imagePath;
    private Location location;
}

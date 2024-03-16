package com.seed_planner.schedule_center.plan.domain;

import java.time.LocalDateTime;

public class SubScheduleDomain {
    private ScheduleDomain schedule;
//    @Column(nullable = false, length = 50)
    private String title;
//    @Column(nullable = false)
    private LocalDateTime startedAt;
//    @Column(nullable = false)
    private LocalDateTime endedAt;
//    @Column(length = 50)
    private String place;
    private Location location;
    private Memo memo;
    private String imagePath;
}

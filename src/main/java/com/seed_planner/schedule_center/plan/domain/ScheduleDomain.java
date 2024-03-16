package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDomain {
    private MemberDomain member;
//    @Column(nullable = false, length = 50)
    private String name;
//    @Column(nullable = false)
    private LocalDateTime startedAt;
//    @Column(nullable = false)
    private LocalDateTime endedAt;
//    @Column(length = 50)
    private String place;
    private Location location;
    private Memo memo;
    private String imagePath;
    private String participantsId;
    private String categoryId;
}

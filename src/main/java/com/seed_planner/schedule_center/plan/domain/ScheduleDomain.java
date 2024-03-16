package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleDomain {
    private MemberDomain member; //필수
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
    private String[] participantsId;
    private String categoryId;

    ScheduleDomain(MemberDomain member, String title, LocalDateTime startedAt, LocalDateTime endedAt) {
        if (member == null || title == null || startedAt == null || endedAt == null)

    }
    public ScheduleDomain(Memo memo, Location location) {

    }
}

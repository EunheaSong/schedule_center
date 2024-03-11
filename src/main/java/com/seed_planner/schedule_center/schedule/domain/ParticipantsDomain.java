package com.seed_planner.schedule_center.schedule.domain;

import com.seed_planner.schedule_center.common.model.BaseDomain;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParticipantsDomain {
    private String name;
    private String imagePath;
    private MemberDomain member;
    private BaseDomain baseDomain;

    private ParticipantsDomain(String imagePath) {
        this.imagePath = imagePath;
    }
    public ParticipantsDomain(String name, String imagePath) {
        this.imagePath = imagePath;
        setName(name);
    }

    private void setName(String name) {
        if (name.length() > 30) throw new IllegalArgumentException("participants name of max length is 30");
        this.name = name;
    }
}

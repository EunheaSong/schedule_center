package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.common.model.BaseDomain;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ParticipantsDomain {
    private final String name;
    private String imagePath;
    private MemberDomain member;
    private BaseDomain baseDomain;

    public ParticipantsDomain(String name, String imagePath) {
        setName(name);
        this.imagePath = imagePath;
        this.name = name;
    }

    public ParticipantsDomain(String name, String imagePath, String id) {
        setName(name);
        this.imagePath = imagePath;
        this.baseDomain = new BaseDomain(id);
        this.name = name;
    }

    private void setName(String name) {
        if (name.length() > 30) throw new IllegalArgumentException("participants name of max length is 30");
        if (name.isBlank()) throw new IllegalArgumentException("participants name is blank.");
    }
}

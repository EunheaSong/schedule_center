package com.seed_planner.schedule_center.plan.domain;

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

    public ParticipantsDomain(String name, String imagePath) {
        this.imagePath = imagePath;
        setName(name);
    }

    public ParticipantsDomain(String name, String imagePath, String id) {
        this.imagePath = imagePath;
        this.baseDomain = new BaseDomain(id);
        setName(name);
    }

    private void setName(String name) {
        if (name.length() > 30) throw new IllegalArgumentException("participants name of max length is 30");
        if (name.isBlank()) throw new IllegalArgumentException("participants name is blank.");
        this.name = name;
    }
}

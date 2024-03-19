package com.seed_planner.schedule_center.plan.domain;

import com.google.gson.Gson;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDomain extends BaseSchedule {
    private MemberDomain member;
    private String[] participantsId;
    private String categoryId;

    public String toStringParticipantsId() {
        return new Gson().toJson(this.participantsId);
    }

    @Setter
    public static class Builder extends BaseSchedule.Builder<Builder> {
        private MemberDomain member;
        private String[] participantsId;
        private String categoryId;

        public Builder(String title, LocalDateTime startedAt, LocalDateTime endedAt) {
            super(title, startedAt, endedAt);
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public ScheduleDomain build() {
            return new ScheduleDomain(this);
        }
    }

    private ScheduleDomain(Builder builder) {
        super(builder);
        this.member = builder.member;
        this.categoryId = builder.categoryId;
        this.participantsId = builder.participantsId;
    }

}

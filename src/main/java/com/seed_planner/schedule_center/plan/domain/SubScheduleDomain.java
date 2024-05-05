package com.seed_planner.schedule_center.plan.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class SubScheduleDomain extends BaseSchedule {
    private ScheduleDomain schedule;

    @Setter
    public static class Builder extends BaseSchedule.Builder<Builder> {
        private ScheduleDomain schedule;

        public Builder(String title, LocalDateTime startedAt, LocalDateTime endedAt, ScheduleDomain schedule) {
            super(title, startedAt, endedAt);
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        SubScheduleDomain build() {
            return new SubScheduleDomain(this);
        }
    }
    SubScheduleDomain(Builder builder) {
        super(builder);
        this.schedule = builder.schedule;
    }

}

//    @NotNull
//    private BaseSchedule baseSchedule;
//    @Length(max = 50)
//    private String place;
//    private Location location;
//    private Memo memo;
//    private String imagePath;
//    private BaseDomain baseDomain;
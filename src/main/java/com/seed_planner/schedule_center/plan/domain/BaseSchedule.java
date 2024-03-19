package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.common.model.BaseDomain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
public abstract class BaseSchedule {
    @NotBlank
    @Length(max = 50, message = "")
    private final String title;
//    @NotNull
    private final LocalDateTime startedAt;
//    @NotNull
    private final LocalDateTime endedAt;
    @Length(max = 50, message = "")
    private String place;
    private final Location location;
    private Memo memo;
    private String imagePath;
    private BaseDomain baseDomain;



    @Setter
    public abstract static class Builder <T extends Builder<T>> {
        private String title;
        private LocalDateTime startedAt;
        private LocalDateTime endedAt;
        private String place;
        private Location location;
        private Memo memo;
        private String imagePath;
        private BaseDomain baseDomain;

        public Builder(String title, LocalDateTime startedAt, LocalDateTime endedAt) {
            this.title = title;
            this.startedAt = startedAt;
            this.endedAt = endedAt;
        }
        protected abstract T self();

        abstract BaseSchedule build();
    }

    BaseSchedule(Builder<?> builder) {
        basicInfoValid(builder.startedAt, builder.endedAt);
        this.title = builder.title;
        this.startedAt = builder.startedAt;
        this.endedAt = builder.endedAt;
        this.place = builder.place;
        this.location = builder.location;
        this.memo = builder.memo;
        this.imagePath = builder.imagePath;
        this.baseDomain = builder.baseDomain;
    }

    private void basicInfoValid(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isAfter(endedAt)) throw new IllegalArgumentException("startedAt is after endedAt.");
    }
}

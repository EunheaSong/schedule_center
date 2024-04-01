package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.common.model.BaseDomain;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class BaseSchedule {
    private final String title;
    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;
    private String place;
    private Location location;
    private Memo memo;
    private String imagePath;
    private BaseDomain baseDomain;

    public abstract static class Builder <T extends Builder<T>> {
        private final String title;
        private final LocalDateTime startedAt;
        private final LocalDateTime endedAt;
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

        public T setPlace(String place) {
            this.place = place;
            return self();
        }

        public T setLocation(Location location) {
            this.location = location;
            return self();
        }

        public T setMemo(Memo memo) {
            this.memo = memo;
            return self();
        }

        public T setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return self();
        }

        public T setBaseDomain(BaseDomain baseDomain) {
            this.baseDomain = baseDomain;
            return self();
        }
    }

    BaseSchedule(Builder<?> builder) {
        basicInfoValid(builder.title, builder.startedAt, builder.endedAt);
        placeValid(builder.place);
        this.title = builder.title;
        this.startedAt = builder.startedAt;
        this.endedAt = builder.endedAt;
        this.place = builder.place;
        this.location = builder.location;
        this.memo = builder.memo;
        this.imagePath = builder.imagePath;
        this.baseDomain = builder.baseDomain;
    }

    private void basicInfoValid(String title, LocalDateTime startedAt, LocalDateTime endedAt) {
        if (title.length() > 50) throw new IllegalArgumentException("title length exceeded. param length : " + title.length());
        if (startedAt.isAfter(endedAt)) throw new IllegalArgumentException("startedAt is after endedAt.\nstartedAt : " + startedAt + " endedAt : " + endedAt);
    }

    private void placeValid(String place) {
        if (place != null && place.length() > 50) throw new IllegalArgumentException("place length exceeded. param length : " + place.length());
    }
}

package com.seed_planner.schedule_center.plan.adapter.in.web.dto.res;

import com.seed_planner.schedule_center.plan.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ScheduleItemRes {
        private String id;
        private String title;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime modifiedAt;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startedAt;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endedAt;
        private String memo;
        private String place;
        private Location location;
        private String categoryId;
        private List<String> participantsIdList;
        private List<SubScheduleItemRes> subScheduleItemLis;

}

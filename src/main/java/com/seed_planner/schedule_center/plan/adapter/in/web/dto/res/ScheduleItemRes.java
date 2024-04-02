package com.seed_planner.schedule_center.plan.adapter.in.web.dto.res;

import com.seed_planner.schedule_center.plan.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
    @Setter
    private List<String> participantsIdList;
    @Setter
    private List<SubScheduleItemRes> subScheduleItemLis;

    public ScheduleItemRes(
            String id, String title, LocalDateTime createdAt, LocalDateTime modifiedAt,
            LocalDateTime startedAt, LocalDateTime endedAt, String memo, String place,
            Location location, String categoryId
    ) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.memo = memo;
        this.place = place;
        this.location = location;
        this.categoryId = categoryId;
    }

}

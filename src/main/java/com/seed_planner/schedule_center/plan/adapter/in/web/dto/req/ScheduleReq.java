package com.seed_planner.schedule_center.plan.adapter.in.web.dto.req;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.seed_planner.schedule_center.plan.domain.Location;
import com.seed_planner.schedule_center.plan.domain.Memo;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ScheduleReq {
    @NotBlank
    @Length(max = 50)
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull(message = "")
    private LocalDateTime startedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull(message = "")
    private LocalDateTime endedAt;
    @Length(max = 50)
    private String place;
    private String imagePath;
    private List<String> participantsId;
    private String categoryId;
    private Location location;
    private Memo memo;

    public ScheduleDomain of (Class<ScheduleDomain> scheduleDomainClass) {
        return new ScheduleDomain.Builder(this.title, this.startedAt, this.endedAt)
                .setPlace(this.place)
                .setImagePath(this.imagePath)
                .setParticipantsId(this.participantsId)
                .setCategoryId(this.categoryId)
                .setLocation(this.location)
                .setMemo(this.memo)
                .build();
    }
}

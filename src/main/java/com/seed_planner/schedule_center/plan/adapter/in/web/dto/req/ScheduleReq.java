package com.seed_planner.schedule_center.plan.adapter.in.web.dto.req;

import com.seed_planner.schedule_center.plan.domain.Location;
import com.seed_planner.schedule_center.plan.domain.Memo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleReq {
    @NotBlank
    @Length(max = 50)
    private String title;
    @NotNull(message = "")
    private LocalDateTime startedAt;
    @NotNull(message = "")
    private LocalDateTime endedAt;
    @Length(max = 50)
    private String place;
    private String imagePath;
    private String[] participantsId;
    private String categoryId;
    private Location location;
    private Memo memo;
}

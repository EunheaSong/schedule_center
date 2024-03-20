package com.seed_planner.schedule_center.plan.adapter.in.web.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParticipantsUpdateReq {
    @NotBlank(message = "name is null")
    @Size(max = 30, message = "max size of name is 30")
    private String name;
    private String imagePath;
    @NotBlank(message = "id is null")
    private String id;
}

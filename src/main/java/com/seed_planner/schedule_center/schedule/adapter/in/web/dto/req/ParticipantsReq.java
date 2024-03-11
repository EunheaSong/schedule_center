package com.seed_planner.schedule_center.schedule.adapter.in.web.dto.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ParticipantsReq {
    @NotBlank(message = "name is null")
    @Size(max = 30, message = "max size of name is 30")
    private String name;
    private String imagePath;
}

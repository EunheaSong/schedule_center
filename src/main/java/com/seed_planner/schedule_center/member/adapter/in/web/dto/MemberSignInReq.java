package com.seed_planner.schedule_center.member.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberSignInReq {
    @NotBlank(message = "email should be not null.")
    private String email;
    @NotBlank(message = "password should be not null.")
    private String password;
}

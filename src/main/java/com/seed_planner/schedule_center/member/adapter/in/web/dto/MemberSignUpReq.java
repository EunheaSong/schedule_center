package com.seed_planner.schedule_center.member.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpReq {

    @NotBlank(message = "email is null.")
    @Email(message = "This is not email from.")
    private String email;

    @NotBlank(message = "password is null.")
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z0-9!@#$%^&*]{8,12}$")
    private String password;

    @NotBlank(message = "re-enter password is null.")
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z0-9!@#$%^&*]{8,12}$")
    private String rePassword;
}

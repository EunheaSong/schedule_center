package com.seed_planner.schedule_center.common.exceptionHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomResponse {
    private String code;
    private String message;

    public CustomResponse(ExceptionCode exceptionCode) {
        this.code = exceptionCode.name();
        this.message = exceptionCode.getMessage();
    }

}

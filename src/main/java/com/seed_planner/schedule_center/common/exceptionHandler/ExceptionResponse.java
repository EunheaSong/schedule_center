package com.seed_planner.schedule_center.common.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse extends CustomResponse {
    private Object detail;

    ExceptionResponse(ExceptionCode exceptionCode, Object value) {
        super(exceptionCode);
        this.detail = value;
    }

}
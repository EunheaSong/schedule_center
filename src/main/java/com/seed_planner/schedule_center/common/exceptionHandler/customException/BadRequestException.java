package com.seed_planner.schedule_center.common.exceptionHandler.customException;

import com.seed_planner.schedule_center.common.exceptionHandler.CustomResponse;
import lombok.Getter;

@Getter
public class BadRequestException extends IllegalArgumentException {
    private final CustomResponse errorMessage;

    BadRequestException(CustomResponse errorMessage){
        this.errorMessage = errorMessage;
    }
}

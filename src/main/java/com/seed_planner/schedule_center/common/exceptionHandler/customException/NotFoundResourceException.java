package com.seed_planner.schedule_center.common.exceptionHandler.customException;

import com.seed_planner.schedule_center.common.exceptionHandler.CustomResponse;
import lombok.Getter;

@Getter
public class NotFoundResourceException extends IllegalArgumentException {

    private final CustomResponse errorMessage;

    public NotFoundResourceException(CustomResponse errorMessage) { this.errorMessage = errorMessage; }
}

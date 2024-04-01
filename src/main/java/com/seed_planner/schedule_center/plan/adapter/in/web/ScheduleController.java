package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleUpdateInPort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.seed_planner.schedule_center.common.Utils.getMemberId;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    private final ScheduleUpdateInPort scheduleUpdateInPort;

    @PostMapping("")
    public ResponseEntity<String> createSchedule(
        @RequestBody @Valid ScheduleReq req, HttpServletRequest request
    ){
        return  ResponseEntity.status(HttpStatus.CREATED).body(scheduleUpdateInPort.create(req, getMemberId(request)));
    }

}

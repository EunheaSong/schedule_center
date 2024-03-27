package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleUpdateInPort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    //생성
    @PostMapping("")
    public ResponseEntity<String> createSchedule(
        @RequestBody @Valid ScheduleReq req, HttpServletRequest request
    ){
        return ResponseEntity.ok(scheduleUpdateInPort.create(req, getMemberId(request)));
    }

    //기간 별 조회 - 캘린더 표시에 필요한 정보

    //단건 조회 - 특정 날짜에 대한 정보

    //수정

    //삭제
}

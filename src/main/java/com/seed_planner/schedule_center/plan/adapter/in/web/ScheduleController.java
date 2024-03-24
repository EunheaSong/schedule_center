package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleController {



    //생성
    @PostMapping("")
    public String createSchedule(
        @RequestBody @Valid ScheduleReq req
    ){
        return "test";
    }

    //기간 별 조회 - 캘린더 표시에 필요한 정보

    //단건 조회 - 특정 날짜에 대한 정보

    //수정

    //삭제
}

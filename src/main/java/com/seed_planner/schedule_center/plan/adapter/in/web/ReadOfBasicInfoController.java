package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.BasicInfoRes;
import com.seed_planner.schedule_center.plan.application.port.in.BasicInfoPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.seed_planner.schedule_center.common.Utils.getMemberId;

@RequiredArgsConstructor
@RequestMapping("/basic/info")
@RestController
public class ReadOfBasicInfoController {

    private final BasicInfoPort basicInfoPort;
    
    @GetMapping("")
    public ResponseEntity<BasicInfoRes> readParticipantsAndCategory(HttpServletRequest request) {
        return ResponseEntity.ok(basicInfoPort.readParticipantsAndCategory(getMemberId(request)));
    }
}

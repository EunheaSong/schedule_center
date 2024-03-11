package com.seed_planner.schedule_center.schedule.adapter.in.web;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipnatsRes;
import com.seed_planner.schedule_center.schedule.application.port.in.ParticipantsCRUDInPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/participants")
@RestController
public class ParticipantsController {
    private final ParticipantsCRUDInPort participantsCRUDInPort;

    //참여자 생성
    @PostMapping("")
    public ResponseEntity<List<ParticipnatsRes>> createParticipants(
        @RequestBody List<ParticipantsReq> req,
        HttpServletRequest request
    ) {
        return ResponseEntity.ok(participantsCRUDInPort.createParticipants(req, request.getAttribute("memberId").toString()));
    }
}

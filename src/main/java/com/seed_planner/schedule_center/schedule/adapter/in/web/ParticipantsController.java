package com.seed_planner.schedule_center.schedule.adapter.in.web;

import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.req.ParticipantsUpdateReq;
import com.seed_planner.schedule_center.schedule.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.schedule.application.port.in.ParticipantsCRUDInPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/participants")
@RestController
public class ParticipantsController {
    private final ParticipantsCRUDInPort participantsCRUDInPort;

    //참여자 생성
    @PostMapping("")
    public ResponseEntity<List<ParticipantsRes>> createParticipants(
        @RequestBody List<ParticipantsReq> req,
        HttpServletRequest request
    ) {
        return ResponseEntity.ok(participantsCRUDInPort.createParticipants(req, request.getAttribute("memberId").toString()));
    }

    //수정
    @PutMapping("")
    public ResponseEntity<List<ParticipantsRes>> updateParticipants(
        @RequestBody List<ParticipantsUpdateReq> req,
        HttpServletRequest request
    ) {
        return ResponseEntity.ok(participantsCRUDInPort.updateParticipants(req, request.getAttribute("memberId").toString()));
    }
}

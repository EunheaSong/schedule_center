package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.seed_planner.schedule_center.plan.application.port.in.ParticipantsUpdateInPort;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsUpdateReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.seed_planner.schedule_center.common.Utils.getMemberId;

@RequiredArgsConstructor
@RequestMapping("/participants")
@RestController
public class ParticipantsController {
    private final ParticipantsUpdateInPort participantsUpdateInPort;

    @PostMapping("")
    public ResponseEntity<List<ParticipantsRes>> createParticipants(
        @RequestBody @Valid List<ParticipantsReq> req,
        HttpServletRequest request
    ) {
        return ResponseEntity.ok(participantsUpdateInPort.createParticipants(req, getMemberId(request)));
    }

    @PutMapping("")
    public ResponseEntity<List<ParticipantsRes>> updateParticipants(
        @RequestBody @Valid List<ParticipantsUpdateReq> req,
        HttpServletRequest request
    ) {
        return ResponseEntity.ok(participantsUpdateInPort.updateParticipants(req, getMemberId(request)));
    }

    @DeleteMapping("")
    public ResponseEntity<String[]> deleteParticipants(
        @RequestBody String[] req,
        HttpServletRequest request
    ) {
        return ResponseEntity.ok(participantsUpdateInPort.deleteParticipants(req, getMemberId(request)));
    }
}

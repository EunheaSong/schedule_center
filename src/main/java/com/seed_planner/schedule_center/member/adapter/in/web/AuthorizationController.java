package com.seed_planner.schedule_center.member.adapter.in.web;

import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignUpReq;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthCheckPort authCheckPort;

    @GetMapping("/exist")
    public ResponseEntity<Boolean> existByEmail(@RequestParam String email) {
        return ResponseEntity.ok(authCheckPort.existByEmail(email));
    }

    @PostMapping("/sign-up")
    public void memberSignIn(@RequestBody @Valid MemberSignUpReq req) {
        authCheckPort.memberSignUp(req);
    }
}

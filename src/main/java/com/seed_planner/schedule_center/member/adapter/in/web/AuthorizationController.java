package com.seed_planner.schedule_center.member.adapter.in.web;

import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignInReq;
import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignUpReq;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
    public void memberSignUp(@RequestBody @Valid MemberSignUpReq req) {
        authCheckPort.memberSignUp(req);
    }

    @PostMapping("/sign-in")
    public void memberSignIn(@RequestBody @Valid MemberSignInReq req, HttpServletResponse response) {
        response.addHeader(HttpHeaders.AUTHORIZATION, authCheckPort.memberSignIn(req, response));
    }
}

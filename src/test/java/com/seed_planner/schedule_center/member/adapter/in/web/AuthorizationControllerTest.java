package com.seed_planner.schedule_center.member.adapter.in.web;

import com.google.gson.Gson;
import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignInReq;
import com.seed_planner.schedule_center.member.adapter.in.web.dto.MemberSignUpReq;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = AuthorizationController.class)
class AuthorizationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AuthCheckPort authCheckPort;

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("회원 가입 테스트")
    void memberSignUp() throws Exception {
        MemberSignUpReq req = new MemberSignUpReq(
            "eeee@email.com",
            "1234qwer@",
            "1234qwer@"
        );
         mockMvc.perform(
            MockMvcRequestBuilders.post("/member/sign-up")
                .content(gson.toJson(req))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andDo(print())
            .andReturn();
    }

    @Test
    @DisplayName("로그인 테스트")
    void memberSignIn() throws Exception {
        MemberSignInReq req = new MemberSignInReq(
            "eeee@email.com",
            "1234qwer@"
        );
        HttpServletResponse response = mock(HttpServletResponse.class);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/member/sign-in")
                    .content(gson.toJson(req))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
            .andDo(print())
            .andReturn();
    }

}
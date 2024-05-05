package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seed_planner.schedule_center.common.GsonUtils;
import com.seed_planner.schedule_center.common.exceptionHandler.GlobalExceptionHandler;
import com.seed_planner.schedule_center.common.intercepter.AuthInterceptor;
import com.seed_planner.schedule_center.common.jwt.JwtProvider;
import com.seed_planner.schedule_center.common.jwt.JwtProviderImpl;
import com.seed_planner.schedule_center.member.application.port.in.AuthCheckPort;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleInfoInPort;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleUpdateInPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = ScheduleController.class)
class ScheduleControllerTest {
    private MockMvc mockMvc;

    private Gson gson;

    @Autowired
    private ScheduleController scheduleController;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private ScheduleInfoInPort scheduleInfoInPort;

    @MockBean
    private ScheduleUpdateInPort scheduleUpdateInPort;

    @BeforeEach
    public void init() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonUtils.LocalDateTimeAdapter()).create();
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController)
            .addInterceptors(new AuthInterceptor(jwtProvider))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    public static ScheduleReq basicScheduleReq(String title, LocalDateTime startedAt, LocalDateTime endedAt) {
        return new ScheduleReq(
            title, startedAt, endedAt,
            null, null, null,null, null, null
        );
    }

    private String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6IjIyY2EyODdmMjFjMTRmNSIsImlhdCI6MTcxMTU0NDM2OCwiZXhwIjoxNzExNTQ3OTY4fQ.WUkXDn7JTXRk3TfT9-ToEiiY7QyxsBs3u3EUVje5uEc";

    @Test
    @DisplayName("create schedule")
    public void createSchedule() throws Exception {
        ScheduleReq req = basicScheduleReq("뽀뇨랑 놀이공원", LocalDateTime.now(), LocalDateTime.now());
        when(jwtProvider.isValidToken(anyString())).thenReturn("memberId");

        mockMvc.perform(post("/schedule")
                .content(gson.toJson(req))
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token)
        ).andExpect(status().isCreated())
            .andDo(print())
            .andReturn();

        then(scheduleUpdateInPort).should().create(any(ScheduleReq.class), eq("memberId"));
    }

    @Test
    @DisplayName("request dto 유효성 검증 : title 길이 초과")
    public void createSchedule_validationCheck1() throws Exception {
        ScheduleReq req = basicScheduleReq("뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원 뽀뇨랑 놀이공원",
            LocalDateTime.now(), LocalDateTime.now());
        when(jwtProvider.isValidToken(anyString())).thenReturn("memberId");

        mockMvc.perform(post("/schedule")
                .content(gson.toJson(req))
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token)
            ).andExpect(status().isBadRequest())
            .andExpect(result -> {
                    if(!(result.getResolvedException() instanceof MethodArgumentNotValidException)) throw new AssertionError();
                }
            )
            .andDo(print())
            .andReturn();

        assertTrue(req.getTitle().length() > 50);
    }


    @Test
    @DisplayName("")
    public void getScheduleDetailItem() throws Exception {
        when(jwtProvider.isValidToken(anyString())).thenReturn("memberId");

        mockMvc.perform(get("/schedule/{id}", "scheduleId")
                .header(AUTHORIZATION, token)
            ).andExpect(status().isOk())
            .andDo(print())
            .andReturn();

        then(scheduleInfoInPort).should().getScheduleDetailItem("scheduleId", "memberId");
    }

}
package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seed_planner.schedule_center.common.GsonUtils;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.common.exceptionHandler.GlobalExceptionHandler;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScheduleControllerTest extends TestSetUp {
    private MockMvc mockMvc;
    private Gson gson;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    public void init() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonUtils.LocalDateTimeAdapter()).create();
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    public static ScheduleReq basicScheduleReq(String title, LocalDateTime startedAt, LocalDateTime endedAt) {
        return new ScheduleReq(
            title, startedAt, endedAt,
            null, null, null,null, null, null
        );
    }

    private String token = "";

    @Test
    @DisplayName("create schedule")
    public void createSchedule() throws Exception {
        String url = "/schedule";
        ScheduleReq req = basicScheduleReq("뽀뇨랑 놀이공원", LocalDateTime.now(), LocalDateTime.now());
        ResultActions result =  mockMvc.perform(
            MockMvcRequestBuilders.post(url)
                .content(gson.toJson(req))
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token) // TODO : 왜 토큰 에러가 발생하지 않는지 ...?
        );
        result.andExpect(status().isOk());

        assertEquals(15, result.andReturn().getResponse().getContentAsString().length());
    }

}
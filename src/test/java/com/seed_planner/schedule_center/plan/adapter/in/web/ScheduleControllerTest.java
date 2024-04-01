package com.seed_planner.schedule_center.plan.adapter.in.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seed_planner.schedule_center.common.GsonUtils;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.common.exceptionHandler.GlobalExceptionHandler;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleUpdateInPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScheduleControllerTest extends TestSetUp {
    private MockMvc mockMvc;
    private Gson gson;

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleUpdateInPort scheduleUpdateInPort;

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

    private String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6IjIyY2EyODdmMjFjMTRmNSIsImlhdCI6MTcxMTU0NDM2OCwiZXhwIjoxNzExNTQ3OTY4fQ.WUkXDn7JTXRk3TfT9-ToEiiY7QyxsBs3u3EUVje5uEc";

    @Test
    @DisplayName("create schedule")
    public void createSchedule() throws Exception {
        String url = "/schedule";
        ScheduleReq req = basicScheduleReq("뽀뇨랑 놀이공원", LocalDateTime.now(), LocalDateTime.now());

//        doReturn("123456789123456").when(scheduleService).create(req, "EEEEE");

        ResultActions result =  mockMvc.perform(
            MockMvcRequestBuilders.post(url)
                .content(gson.toJson(req))
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, token)
                .requestAttr("memberId", "EEEEE")
        );
        result.andExpect(status().isCreated());
        //result.andReturn().getResponse().getContentAsString() 이 왜 null 인지...
//        assertEquals(15, result.andReturn().getResponse().getContentAsString().length());
    }


}
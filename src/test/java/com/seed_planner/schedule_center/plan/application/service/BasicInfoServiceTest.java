package com.seed_planner.schedule_center.plan.application.service;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.BasicInfoRes;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.CategoryRes;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.plan.application.port.out.CategoryInfoPort;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsInfoPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasicInfoServiceTest {
    @Autowired
    private ParticipantsInfoPort participantsInfoPort;
    @Autowired
    private CategoryInfoPort categoryInfoPort;

    @Test
    @DisplayName("기본 정보 조회 테스트 - 멤버별 조회")
    void readParticipantsAndCategory() {
        String allNotEmptyMemberId = "22ca287f21c14f5";
        String allEmptyMemberId = "fe362de878484a4";
        BasicInfoRes res1 = new BasicInfoRes(
            participantsInfoPort.getBasicInfoAllByMemberId(allNotEmptyMemberId),
            categoryInfoPort.getBasicInfoAllByMemberId(allNotEmptyMemberId));
        BasicInfoRes res2 =  new BasicInfoRes(
            participantsInfoPort.getBasicInfoAllByMemberId(allEmptyMemberId),
            categoryInfoPort.getBasicInfoAllByMemberId(allEmptyMemberId));

        assertFalse(res1.getCategoryList().isEmpty());
        assertFalse(res1.getParticipantsList().isEmpty());
        assertTrue(res2.getCategoryList().isEmpty());
        assertTrue(res2.getParticipantsList().isEmpty());
    }

    @Test
    @DisplayName("기본 정보 조회 테스트 - 목록 값 확인")
    void readParticipantsAndCategory2() {
        String allNotEmptyMemberId = "22ca287f21c14f5";
        BasicInfoRes res = new BasicInfoRes(
            participantsInfoPort.getBasicInfoAllByMemberId(allNotEmptyMemberId),
            categoryInfoPort.getBasicInfoAllByMemberId(allNotEmptyMemberId));
        List<CategoryRes> categoryResList = res.getCategoryList();
        List<ParticipantsRes> participantsResList = res.getParticipantsList();

        assertEquals(1, categoryResList.size());
        assertEquals(6, participantsResList.size());
        assertEquals("노랑", categoryResList.get(0).getColor());
    }
}
package com.seed_planner.schedule_center.schedule.application.service;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ParticipantsUpdateReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ParticipantsRes;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsCRUDOutPort;
import com.seed_planner.schedule_center.plan.domain.ParticipantsDomain;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ParticipantsServiceTest {


    @Autowired
    private final ParticipantsCRUDOutPort crudOutPort;

    String memberId = "22ca287f21c14f5";
    String name = "";
    String imagePath = null;

    ParticipantsServiceTest(ParticipantsCRUDOutPort crudOutPort) {
        this.crudOutPort = crudOutPort;
    }

    @BeforeAll
    static void setUp() {

    }

    @Test
    @DisplayName("참가자 생성")
    void createParticipants() {
        name = "참가자1";
        List<ParticipantsReq> req = List.of(
            new ParticipantsReq(name, imagePath)
        );
        List<ParticipantsDomain> domainList = req.stream()
            .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath()))
            .toList();
        crudOutPort.createParticipants(domainList, memberId);
    }

    @Test
    @DisplayName("참가자 이름 값 검증 실패 - 빈값")
    void createParticipantsFail() {
        String exceptionM = "";
        List<ParticipantsReq> req = List.of(
            new ParticipantsReq(name, imagePath)
        );
        try {
        req.stream()
            .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath()))
            .toList();
        } catch (Exception e) {
            exceptionM = e.getMessage();
        }
        assertEquals("participants name is blank.", exceptionM);
    }

    @Test
    @DisplayName("참가자 이름 값 검증 실패 - 30자 초과")
    void createParticipantsFail2() {
        name = "처음 본 널 기억해 we skipped the small tack 바로 다음 단계였지 뭐";
        String exceptionM = "";
        List<ParticipantsReq> req = List.of(
            new ParticipantsReq(name, imagePath)
        );
        try {
            req.stream()
                .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath()))
                .toList();
        } catch (Exception e) {
            exceptionM = e.getMessage();
        }
        assertTrue(name.length() > 30);
        assertEquals("participants name of max length is 30", exceptionM);
    }


    @Test
    @DisplayName("참가자 업데이트 성공")
    void updateParticipants() {
        name = "참가자를 변경합니다.";
        String id = "0d8b37a22e9b421";
        List<ParticipantsUpdateReq> req = List.of(
          new ParticipantsUpdateReq(name, imagePath, id)
        );
        List<ParticipantsDomain> domainList = req.stream()
            .map(it -> new ParticipantsDomain(it.getName(), it.getImagePath(), it.getId()))
            .collect(Collectors.toList());
        List<ParticipantsRes> res = crudOutPort.updateParticipants(domainList, memberId);
        assertEquals(name, res.get(0).getName());
    }
}
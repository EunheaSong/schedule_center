package com.seed_planner.schedule_center.plan.application.service;


import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.common.model.BaseDomain;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.application.port.out.ExternallyMemberPort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItem;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItemRes;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsInfoPort;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleInfoOutPort;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleUpdateOutPort;
import com.seed_planner.schedule_center.plan.application.port.out.SubScheduleInfoOutPort;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;


//@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest extends TestSetUp {
    private final ScheduleUpdateOutPort scheduleUpdateOutPort =
        Mockito.mock(ScheduleUpdateOutPort.class);

    private final ExternallyMemberPort externallyMemberPort =
        Mockito.mock(ExternallyMemberPort.class);

    private final ScheduleInfoOutPort scheduleInfoOutPort =
        Mockito.mock(ScheduleInfoOutPort.class);

    private final ParticipantsInfoPort participantsInfoPort =
        Mockito.mock(ParticipantsInfoPort.class);

    private final SubScheduleInfoOutPort subScheduleInfoOutPort =
        Mockito.mock(SubScheduleInfoOutPort.class);

    private final ScheduleService scheduleService =
        new ScheduleService(scheduleUpdateOutPort, externallyMemberPort, scheduleInfoOutPort, participantsInfoPort, subScheduleInfoOutPort);

    public SubScheduleItemRes createSubScheduleItemRes(String id) {
        return new SubScheduleItemRes(
            id,
            "",
            LocalDateTime.of(2024, 4, 15, 10, 0, 0),
            LocalDateTime.of(2024, 4, 15, 10, 0, 0),
            "",
            "",
            "",
            null
        );
    }

    private final static String wrongScheduleId = "wrong";
    private final static String correctScheduleId = "correct";

    public static MemberDomain memberDomain;
    public static MemberEntity memberEntity;

    @BeforeEach
    void setUp() {
        memberEntity = new MemberEntity("email@aaaa.com", "passsss", "");
        memberDomain = new MemberDomain(
            memberEntity.getEmail(),
            memberEntity.getPassword(),
            memberEntity.getKakaoId(),
            new BaseDomain(
                memberEntity.getId(),
                memberEntity.getCreatedAt(),
                memberEntity.getModifiedAt(),
                memberEntity.isDeleted()
            )
        );
    }

    @Test
    @DisplayName("schedule 생성")
    public void createSchedule() {
        String scheduleId = "schedule123";
        String title = "뽀뇨랑 밥먹기";
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = LocalDateTime.now();
        ScheduleReq req = new ScheduleReq(title, startedAt, endedAt, null, null, null, null, null, null);

        given(externallyMemberPort.getActivationMember(anyString())).willReturn(memberDomain);
//        doReturn(memberDomain).when(externallyMemberPort).getActivationMember(anyString());

        given(scheduleUpdateOutPort.create(any(ScheduleDomain.class), eq(memberDomain))).willReturn(scheduleId);
//        doReturn(scheduleId).when(scheduleUpdateOutPort).create(any(ScheduleDomain.class), eq(memberDomain));

        String result = scheduleService.create(req, memberEntity.getId());

        assertEquals(scheduleId, result);
    }

    @Test
    @DisplayName("schedule 생성 실패 : id에 맞는 참여자가 존재하지 않음.")
    public void createSchedule_existNotParticipantsByParticipantsId() {
    }


    @Test
    @DisplayName("날짜별 schedule 단 건 조회 성공")
    void successGetScheduleItemRes() {
        List<String> participantsIds = List.of("엄마", "친구");
        List<SubScheduleItemRes> subScheduleItemResList = List.of(createSubScheduleItemRes("subSchedule"));

        getScheduleItemByCorrectId();
        getParticipantsIdListByCorrectScheduleId(participantsIds);
        getSubScheduleByCorrectScheduleId(subScheduleItemResList);

        final ScheduleItemRes result = scheduleService.getScheduleDetailItem(correctScheduleId, "sss");

        //schedule data
        assertEquals(correctScheduleId, result.getScheduleItem().getId());
        //participants data
        assertEquals(participantsIds.get(0), result.getParticipantsIdList().get(0));
        //subSchedule data
        assertEquals(subScheduleItemResList.get(0), result.getSubScheduleItemLis().get(0));

        verify(scheduleInfoOutPort, times(1)).getScheduleItemRes(eq(correctScheduleId), anyString());
        verify(participantsInfoPort, times(1)).getParticipantsIdListByScheduleId(eq(correctScheduleId));
        verify(subScheduleInfoOutPort, times(1)).getSubScheduleItemListBySchedule(eq(correctScheduleId));

    }

    @Test
    @DisplayName("날짜별 schedule 단 건 조회 - 실패")
    void failGetScheduleItemRes() {
        getScheduleItemByWrongId();
        getParticipantsIdListByWrongScheduleId();
        getSubScheduleByWrongScheduleId();

        final ScheduleItemRes result = scheduleService.getScheduleDetailItem(wrongScheduleId, "sss");

        //schedule data
        assertTrue(result.getScheduleItem() == null);
        //participants data
        assertTrue(result.getParticipantsIdList().isEmpty());
        //subSchedule data
        assertTrue(result.getSubScheduleItemLis().isEmpty());

        verify(scheduleInfoOutPort, times(1)).getScheduleItemRes(eq(wrongScheduleId), anyString());
        verify(participantsInfoPort, times(1)).getParticipantsIdListByScheduleId(eq(wrongScheduleId));
        verify(subScheduleInfoOutPort, times(1)).getSubScheduleItemListBySchedule(eq(wrongScheduleId));
    }

    public ScheduleItem createScheduleItem(String id) {
        return new ScheduleItem(
            id,
            "디롱이랑 유튭 일정",
            LocalDateTime.of(2024, 4, 15, 10, 0, 0),
            LocalDateTime.of(2024, 4, 15, 10, 0, 0),
            LocalDateTime.of(2024, 4, 21, 10, 0, 0),
            LocalDateTime.of(2024, 4, 21, 15, 0, 0),
            "",
            "",
            null,
            ""
        );
    }

    public void getScheduleItemByCorrectId() {
        ScheduleItem scheduleItem = createScheduleItem(correctScheduleId);
        given(scheduleInfoOutPort.getScheduleItemRes(eq(correctScheduleId), anyString())).willReturn(scheduleItem);
//        doReturn(scheduleItem).when(scheduleInfoOutPort).getScheduleItemRes(eq(correctScheduleId), anyString());
    }

    public void getScheduleItemByWrongId() {
        given(scheduleInfoOutPort.getScheduleItemRes(eq(wrongScheduleId), anyString())).willReturn(null);
    }

    public void getParticipantsIdListByCorrectScheduleId(List<String> idList) {
        given(participantsInfoPort.getParticipantsIdListByScheduleId(eq(correctScheduleId)))
            .willReturn(idList);
    }

    public void getParticipantsIdListByWrongScheduleId() {
        List<String> emptyList = new ArrayList<>();
        given(participantsInfoPort.getParticipantsIdListByScheduleId(eq(wrongScheduleId)))
            .willReturn(emptyList);
    }

    public void getSubScheduleByCorrectScheduleId(List<SubScheduleItemRes> subScheduleItemResList) {
        given(subScheduleInfoOutPort.getSubScheduleItemListBySchedule(correctScheduleId)).willReturn(subScheduleItemResList);
    }

    public void getSubScheduleByWrongScheduleId() {
        List<SubScheduleItemRes> emptyList = new ArrayList<>();
        given(subScheduleInfoOutPort.getSubScheduleItemListBySchedule(wrongScheduleId)).willReturn(emptyList);
    }

}

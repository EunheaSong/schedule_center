package com.seed_planner.schedule_center.plan.application.service;


import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberPersistencePort;
import com.seed_planner.schedule_center.member.domain.MemberDomain;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleUpdateOutPort;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@DataJpaTest
@Import(TestConfig.class)
public class ScheduleServiceTest extends TestSetUp {
    private final Logger logger = LoggerFactory.getLogger(ScheduleServiceTest.class);
    @Mock
    private MemberPersistencePort memberPersistencePort;
    @Mock
    private ScheduleUpdateOutPort scheduleUpdateOutPort;

    private static String title = "뽀뇨랑 밥먹기";
    private static LocalDateTime startedAt = LocalDateTime.now();
    private static LocalDateTime endedAt = LocalDateTime.now();
    public static MemberDomain memberDomain;
    public static MemberEntity memberEntity;

    @BeforeEach
    void setUp() {
        memberDomain = new MemberDomain("email@aaaa.com", "passsss");
        memberEntity = new MemberEntity("email@aaaa.com", "passsss", "");
    }

    @Test
    @DisplayName("schedule 생성")
    public void createSchedule() {
        doReturn(memberDomain).when(memberPersistencePort).getByIdAndIsDeletedFalse(memberEntity.getId());

        ScheduleReq req = new ScheduleReq(title, startedAt, endedAt, null, null, null, null, null, null);
        memberDomain =  memberPersistencePort.getByIdAndIsDeletedFalse(memberEntity.getId());
        ScheduleDomain scheduleDomain = dtoToDomain(req, memberDomain);

        try {
            scheduleUpdateOutPort.create(scheduleDomain, memberDomain);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        assertEquals(memberDomain.getEmail(), memberPersistencePort.getByIdAndIsDeletedFalse(memberEntity.getId()).getEmail());
    }

    @Test
    @DisplayName("schedule 생성 실패 : member data 없음")
    public void createScheduleFail() {
        doNothing().when(memberPersistencePort).getByIdAndIsDeletedFalse(memberEntity.getId());

        ScheduleReq req = new ScheduleReq(title, startedAt, endedAt, null, null, null, null, null, null);
        memberDomain =  memberPersistencePort.getByIdAndIsDeletedFalse(memberEntity.getId());
        dtoToDomain(req, memberDomain);

        assertEquals(memberDomain.getEmail(), memberPersistencePort.getByIdAndIsDeletedFalse(memberEntity.getId()).getEmail());

    }

    public ScheduleDomain dtoToDomain(ScheduleReq req, MemberDomain member) {
        return new ScheduleDomain.Builder(req.getTitle(), req.getStartedAt(), req.getEndedAt())
                .setPlace(req.getPlace())
                .setLocation(req.getLocation())
                .setImagePath(req.getImagePath())
                .setMemo(req.getMemo())
                .setCategoryId(req.getCategoryId())
                .setParticipantsId(req.getParticipantsId())
                .setMember(member)
                .build();
    }
}

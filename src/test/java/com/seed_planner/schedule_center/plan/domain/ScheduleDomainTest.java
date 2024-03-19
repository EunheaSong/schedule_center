package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.common.TestSetUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleDomainTest extends TestSetUp {
    @Test
    @DisplayName("ScheduleDomain 생성 성공")
    public void scheduleBuilderTest() {
        String title = "엄마랑 밥먹기";
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = LocalDateTime.now();
        ScheduleDomain scheduleDomain = new ScheduleDomain.Builder(title, startedAt, endedAt).build();

        assertEquals(title, scheduleDomain.getTitle());
        assertEquals(startedAt, scheduleDomain.getStartedAt());
        assertEquals(endedAt, scheduleDomain.getEndedAt());
        //삽입하지 않은 데이터들
        assertNull(scheduleDomain.getImagePath());
        assertNull(scheduleDomain.getMember());
        assertNull(scheduleDomain.getMemo());
        assertNull(scheduleDomain.getCategoryId());
        assertNull(scheduleDomain.getParticipantsId());
        assertNull(scheduleDomain.getPlace());
        assertNull(scheduleDomain.getLocation());
    }

    @Test
    @DisplayName("ScheduleDomain 생성 실패 : 일정 시작 시각이 종료 시각 보다 큼")
    public void setScheduleMapperFailTest() {
        String title = "아빠랑 밥먹기";
        LocalDateTime startedAt = LocalDateTime.of(2024, 3, 1, 0, 0, 0);
        LocalDateTime endedAt = LocalDateTime.now();
        try {
            new ScheduleDomain.Builder(title, startedAt, endedAt).build();
        } catch (Exception e) {
            assertEquals("startedAt is after endedAt.", e.getMessage());
        }
    }

    @Test
    @DisplayName("ScheduleDomain 생성 실패 : ")
    public void setScheduleMapperFailTest2() {

    }
}
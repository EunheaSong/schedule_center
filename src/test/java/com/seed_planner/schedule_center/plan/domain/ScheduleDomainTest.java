package com.seed_planner.schedule_center.plan.domain;

import com.seed_planner.schedule_center.common.TestSetUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleDomainTest extends TestSetUp {

    private final Logger logger = LoggerFactory.getLogger(ScheduleDomainTest.class);
    private static String title = "아빠랑 밥먹기";
    private static LocalDateTime startedAt = LocalDateTime.now();
    private static LocalDateTime endedAt = LocalDateTime.now();
    private static String place = "맛도리가 끝내주는 감자탕집";
    private static String memo = "아빠는 어떤 음식을 좋아하시지? 아빠가 좋아하시는 음식을 먹자.";


    @Test
    @DisplayName("ScheduleDomain 생성 성공")
    public void scheduleBuilderTest() {
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
        startedAt = LocalDateTime.of(2024, 3, 1, 0, 0, 0);
        endedAt = LocalDateTime.now();
        try {
            new ScheduleDomain.Builder(title, startedAt, endedAt).build();
        } catch (Exception e) {
            assertEquals("startedAt is after endedAt.\nstartedAt : " + startedAt + " endedAt : " + endedAt, e.getMessage());
        }
    }

    @Test
    @DisplayName("ScheduleDomain 생성 실패 : 일정 제목 길이 초과")
    public void setScheduleMapperFailTest2() {
        title = "Falling in love. 너에게 난 옵션, 시작부터 다른 너와 나. 깨지는 Heart 빗나간 네 mention 익숙하거든.";
        int length = title.length();
        logger.info("\ntitle length : " + length + "\n" + title);
        assertTrue(length > 50);
        try {
            ScheduleDomain scheduleDomain = new ScheduleDomain.Builder(title, startedAt, endedAt)
                    .setPlace(place)
                    .setMemo(new Memo(memo))
                    .build();
            logger.info(scheduleDomain.getTitle());

        } catch (Exception e) {
            assertEquals("title length exceeded. param length : " + length, e.getMessage(), e.getMessage());
        }
    }

    @Test
    @DisplayName("ScheduleDomain 생성 실패 : 장소명 길이 초과")
    public void setScheduleMapperFailTest3() {
        place = "떠나요 둘이서 모든 걸 훌훌 버리고 제주도 푸른 밤 그 별 아래. 이제는 더 이상 얽메이긴 우린 싫어요.";
        int length = place.length();
        logger.info("\ntitle length : " + length + "\n" + place);
        assertTrue(length > 50);
        try {
            ScheduleDomain scheduleDomain = new ScheduleDomain.Builder(title, startedAt, endedAt)
                    .setPlace(place)
                    .setMemo(new Memo(memo))
                    .build();
            logger.info(scheduleDomain.getTitle());

        } catch (Exception e) {
            assertEquals("place length exceeded. param length : " + length, e.getMessage(), e.getMessage());
        }
    }

    @Test
    @DisplayName("ScheduleDomain 생성 실패 : 메모 길이 초과")
    public void setScheduleMapperFailTest4() {
        memo = """
                비밀이 하나둘씩 늘어가도 설마설마했어. 또 왠지 오래 걸리는 전화연결에도 애써 담담했어. \n오늘따라 무척 요란하게 내 방 유리창을 흔들어대. oh my got, what's happening
                언제 그칠지도 모르는 체, 구름마저 몰려오는 듯 해. Oh my got, what's happening
                내게 전화할 때, 받지 않는 널 계속 기다리며 난 바라바라바라봐 바람바람바람
                창밖 가득 더 자꾸 뱅뱅뱅 소용돌이쳐대 난 바라바라바라봐 바람바람바람
                """;
        int length = memo.length();
        logger.info("\nmemo length : " + length + "\n" + memo);
        assertTrue(length > 200);

        try {
            new ScheduleDomain.Builder(title, startedAt, endedAt)
                    .setPlace(place)
                    .setMemo(new Memo(memo))
                    .build();
        } catch (Exception e) {
            assertEquals("memo length exceeded.\\n" + "param length : " + length, e.getMessage());
        }
    }


}
package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.plan.domain.Location;
import com.seed_planner.schedule_center.plan.domain.Memo;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.seed_planner.schedule_center.common.Utils.nullCheck;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ScheduleMapperTest {

    private final Logger logger = LoggerFactory.getLogger(ScheduleMapperTest.class);

    ScheduleEntity domainToEntity(ScheduleDomain domain, MemberEntity memberEntity) {
        Location location = nullCheck(domain.getLocation(), new Location(null, null, null));
        Memo meno = nullCheck(domain.getMemo(), new Memo(""));
        ScheduleEntity s =  new ScheduleEntity(
                memberEntity,
                domain.getTitle(),
                domain.getStartedAt(),
                domain.getEndedAt(),
                domain.getPlace(),
                location.getLat(),
                location.getLng(),
                location.getAddress(),
                meno.getContents(),
                domain.getImagePath(),
                domain.toStringParticipantsId(),
                domain.getCategoryId()
        );
        logger.info(s.getId());
        return s;
    }

    @Test
    @DisplayName("domain to entity 성공 테스트")
    public void domainToEntityTest() {
        ScheduleDomain domain = SchedulePersistencePortTest.createSchedule();
        MemberEntity memberEntity = new MemberEntity("email@aaaa.com", "passsss", "");
        ScheduleEntity entity = null;
        try {
            Location location = nullCheck(domain.getLocation(), new Location(null, null, null));
            Memo meno = nullCheck(domain.getMemo(), new Memo(""));
            entity =  new ScheduleEntity(
                    memberEntity,
                    domain.getTitle(),
                    domain.getStartedAt(),
                    domain.getEndedAt(),
                    domain.getPlace(),
                    location.getLat(),
                    location.getLng(),
                    location.getAddress(),
                    meno.getContents(),
                    domain.getImagePath(),
                    domain.toStringParticipantsId(),
                    domain.getCategoryId()
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        assertNotNull(entity);
        assertEquals(domain.getTitle(), entity.getTitle());
    }
}

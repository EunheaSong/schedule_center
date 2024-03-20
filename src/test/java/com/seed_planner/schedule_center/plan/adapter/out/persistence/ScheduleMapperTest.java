package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.plan.domain.Location;
import com.seed_planner.schedule_center.plan.domain.Memo;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.seed_planner.schedule_center.common.Utils.nullCheck;

@ExtendWith(MockitoExtension.class)
class ScheduleMapperTest {
//    private MemberMapper memberMapper;

    ScheduleEntity domainToEntity(ScheduleDomain domain, MemberEntity memberEntity) {
        System.out.println("이런 시발");
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
        System.out.println(s.getId());
        return s;
    }
}

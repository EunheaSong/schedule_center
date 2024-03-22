package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberMapper;
import com.seed_planner.schedule_center.plan.domain.Location;
import com.seed_planner.schedule_center.plan.domain.Memo;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.seed_planner.schedule_center.common.Utils.nullCheck;


@Component
class ScheduleMapper {

    ScheduleEntity domainToEntity(ScheduleDomain domain, MemberEntity memberEntity) {
        Location location = nullCheck(domain.getLocation(), new Location(null, null, null));
        Memo meno = nullCheck(domain.getMemo(), new Memo(""));
        return new ScheduleEntity(
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
    }
}

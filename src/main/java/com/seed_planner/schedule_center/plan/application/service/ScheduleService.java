package com.seed_planner.schedule_center.plan.application.service;

import com.seed_planner.schedule_center.member.application.port.out.ExternallyMemberPort;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.req.ScheduleReq;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.ScheduleItemRes;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleInfoInPort;
import com.seed_planner.schedule_center.plan.application.port.in.ScheduleUpdateInPort;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsInfoPort;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleInfoOutPort;
import com.seed_planner.schedule_center.plan.application.port.out.ScheduleUpdateOutPort;
import com.seed_planner.schedule_center.plan.application.port.out.SubScheduleInfoOutPort;
import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleService implements ScheduleUpdateInPort, ScheduleInfoInPort {
    private final ScheduleUpdateOutPort scheduleUpdateOutPort;
    private final ExternallyMemberPort externallyMemberPort;
    private final ScheduleInfoOutPort scheduleInfoOutPort;
    private final ParticipantsInfoPort participantsInfoPort;
    private final SubScheduleInfoOutPort subScheduleInfoOutPort;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String create(ScheduleReq req, String memberId) {
        ScheduleDomain scheduleDomain = req.of(ScheduleDomain.class);
        return scheduleUpdateOutPort.create(scheduleDomain, externallyMemberPort.getActivationMember(memberId));
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleItemRes getScheduleDetailItem(String id, String memberId) {
        return new ScheduleItemRes(
            scheduleInfoOutPort.getScheduleItemRes(id, memberId),
            participantsInfoPort.getParticipantsIdListByScheduleId(id),
            subScheduleInfoOutPort.getSubScheduleItemListBySchedule(id)
        );
    }
}

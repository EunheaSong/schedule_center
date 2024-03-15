package com.seed_planner.schedule_center.plan.application.service;

import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.BasicInfoRes;
import com.seed_planner.schedule_center.plan.application.port.in.BasicInfoPort;
import com.seed_planner.schedule_center.plan.application.port.out.CategoryInfoPort;
import com.seed_planner.schedule_center.plan.application.port.out.ParticipantsInfoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BasicInfoService implements BasicInfoPort {
    private final ParticipantsInfoPort participantsInfoPort;
    private final CategoryInfoPort categoryInfoPort;

    @Transactional(readOnly = true)
    @Override
    public BasicInfoRes readParticipantsAndCategory(String memberId) {
        return new BasicInfoRes(
            participantsInfoPort.getBasicInfoAllByMemberId(memberId),
            categoryInfoPort.getBasicInfoAllByMemberId(memberId)
        );
    }
}

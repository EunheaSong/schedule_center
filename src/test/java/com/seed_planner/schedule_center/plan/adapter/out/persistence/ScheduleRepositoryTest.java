package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.common.TestConfig;
import com.seed_planner.schedule_center.common.TestSetUp;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberEntity;
import com.seed_planner.schedule_center.member.adapter.out.persistence.MemberRepository;
import com.seed_planner.schedule_center.plan.adapter.in.web.dto.res.SubScheduleItemRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestConfig.class)
@DataJpaTest
class ScheduleRepositoryTest extends TestSetUp {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MemberRepository memberRepository;


    private static MemberEntity member;
    private static ScheduleEntity schedule;


    @BeforeEach
    public void init() {
        member = new MemberEntity("aaaa@email.com", "1234qwer", "");
        memberRepository.save(member);
        schedule = new ScheduleEntity(
                member,
                "제목",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "장소장소",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        scheduleRepository.save(schedule);
    }

    @Test
    public void get() {

        scheduleRepository.get(schedule.getId(), member.getId(), new ArrayList<String>(), new ArrayList<SubScheduleItemRes>());
    }
}
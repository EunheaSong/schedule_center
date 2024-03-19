package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScheduleRepositoryTest {
    @Autowired
    private ScheduleRepository scheduleRepository;

//    @Test
//    public void save() {
//        scheduleRepository.save();
//    }
}
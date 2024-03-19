package com.seed_planner.schedule_center.plan.application.port.out;

import com.seed_planner.schedule_center.plan.domain.ScheduleDomain;
import org.junit.jupiter.api.Test;

public interface ScheduleUpdateOutPortTest {

    void create(ScheduleDomain domain);
}

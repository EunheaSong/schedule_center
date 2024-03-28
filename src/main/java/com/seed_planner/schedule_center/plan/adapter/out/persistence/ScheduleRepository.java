package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl.ScheduleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface ScheduleRepository extends JpaRepository<ScheduleEntity, String>, ScheduleCustomRepository {
}

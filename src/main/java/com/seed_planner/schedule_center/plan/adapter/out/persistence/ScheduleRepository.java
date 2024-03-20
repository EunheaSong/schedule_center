package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ScheduleRepository extends JpaRepository<ScheduleEntity, String> {
}

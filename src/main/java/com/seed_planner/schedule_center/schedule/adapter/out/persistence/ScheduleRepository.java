package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ScheduleRepository extends JpaRepository<ScheduleEntity, String> {
}

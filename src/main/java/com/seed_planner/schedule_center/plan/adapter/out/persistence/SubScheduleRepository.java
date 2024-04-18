package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl.SubScheduleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

interface SubScheduleRepository extends JpaRepository<SubScheduleEntity, String>, SubScheduleCustomRepository {
}

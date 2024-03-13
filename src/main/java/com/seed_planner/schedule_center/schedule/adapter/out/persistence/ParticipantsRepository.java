package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import com.seed_planner.schedule_center.schedule.adapter.out.persistence.querydsl.ParticipantsQRepository;
import org.springframework.data.jpa.repository.JpaRepository;


interface ParticipantsRepository extends JpaRepository<ParticipantsEntity, String>, ParticipantsQRepository {

}

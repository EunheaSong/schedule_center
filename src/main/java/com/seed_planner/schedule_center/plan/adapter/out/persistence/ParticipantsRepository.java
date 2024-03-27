package com.seed_planner.schedule_center.plan.adapter.out.persistence;

import com.seed_planner.schedule_center.plan.adapter.out.persistence.querydsl.ParticipantsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


interface ParticipantsRepository extends JpaRepository<ParticipantsEntity, String>, ParticipantsCustomRepository {
    Set<ParticipantsEntity> findAllByIdInAndIsDeletedFalse(List<String> id);
}

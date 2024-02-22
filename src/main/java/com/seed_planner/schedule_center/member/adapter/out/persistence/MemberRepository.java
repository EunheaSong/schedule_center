package com.seed_planner.schedule_center.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<MemberEntity, String> {
}

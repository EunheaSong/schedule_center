package com.seed_planner.schedule_center.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface MemberRepository extends JpaRepository<MemberEntity, String> {

    boolean existsByEmailAndIsDeleted(String email, boolean isDeleted);

    MemberEntity findByEmailAndIsDeleted(String email, boolean isDeleted);

    @Query("""
        select m.password from MemberEntity m where m.email = :email
    """)
    String getPasswordByEmail(@Param("email") String email);
}

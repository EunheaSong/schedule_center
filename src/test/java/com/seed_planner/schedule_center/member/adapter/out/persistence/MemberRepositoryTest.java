package com.seed_planner.schedule_center.member.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity save(MemberEntity member) {
        return memberRepository.save(member);
    }

    public MemberEntity findByIdAndIsDeletedFalse(String id) {
        return memberRepository.findByIdAndIsDeletedFalse(id);
    }
}
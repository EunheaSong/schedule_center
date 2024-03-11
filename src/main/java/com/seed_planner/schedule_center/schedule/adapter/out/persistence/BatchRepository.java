package com.seed_planner.schedule_center.schedule.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BatchRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void participantsSaveAll(List<ParticipantsEntity> participantsList, String memberId) {
        String sql = "INSERT INTO participants (id, name, image_path, created_at, modified_at, member_id, is_deleted)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
            sql,
            participantsList,
            participantsList.size(),
            (PreparedStatement ps, ParticipantsEntity participants) -> {
                ps.setString(1, participants.getId());
                ps.setString(2, participants.getName());
                ps.setString(3, participants.getImagePath());
                ps.setObject(4, participants.getCreatedAt());
                ps.setObject(5, participants.getModifiedAt());
                ps.setObject(6, memberId);
                ps.setObject(7, participants.isDeleted());

            });
    }
}

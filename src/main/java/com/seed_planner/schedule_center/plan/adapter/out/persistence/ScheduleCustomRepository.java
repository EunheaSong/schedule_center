package com.seed_planner.schedule_center.plan.adapter.out.persistence;

public interface ScheduleCustomRepository {
    void createSchedule(ScheduleEntity scheduleEntity, String memberId);
}

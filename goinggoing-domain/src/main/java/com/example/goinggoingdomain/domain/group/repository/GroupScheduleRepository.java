package com.example.goinggoingdomain.domain.group.repository;

import com.example.goinggoingdomain.domain.group.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupScheduleRepository extends JpaRepository<GroupSchedule, Long> {
    void deleteByScheduleId(long scheduleId);
    GroupSchedule findByScheduleId(Long scheduleId);
}

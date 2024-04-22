package com.example.group;

import com.example.group.GroupScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupScheduleJpaRepository extends JpaRepository<GroupScheduleJpaEntity, Long> {
    void deleteByScheduleId(long scheduleId);
    GroupScheduleJpaEntity findByScheduleId(Long scheduleId);
}

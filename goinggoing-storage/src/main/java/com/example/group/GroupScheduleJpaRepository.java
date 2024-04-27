package com.example.group;

import com.example.group.GroupScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface GroupScheduleJpaRepository extends JpaRepository<GroupScheduleJpaEntity, Long> {
    void deleteByScheduleId(long scheduleId);
    GroupScheduleJpaEntity findByScheduleId(Long scheduleId);
    GroupScheduleJpaEntity findByUserGroup_GroupId(Long groupId);
    @Modifying
    @Query("UPDATE GroupScheduleJpaEntity g " +
            "SET g.scheduleName = :scheduleName, " +
            "    g.scheduleDateTime = :scheduleDateTime, " +
            "    g.scheduleLocation = :scheduleLocation, " +
            "    g.scheduleDescription = :scheduleDescription " +
            "WHERE g.scheduleId = :scheduleId")
    void updateGroupSchedule(
            @Param("scheduleId") Long scheduleId,
            @Param("scheduleName") String scheduleName,
            @Param("scheduleDateTime") LocalDateTime scheduleDateTime,
            @Param("scheduleLocation") String scheduleLocation,
            @Param("scheduleDescription") String scheduleDescription
    );
}

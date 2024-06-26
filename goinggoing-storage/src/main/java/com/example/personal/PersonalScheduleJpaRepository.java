package com.example.personal;

import com.example.personal.PersonalScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalScheduleJpaRepository extends JpaRepository<PersonalScheduleJpaEntity, Long> {
    List<PersonalScheduleJpaEntity> findAllByUserGroup_GroupId(Long groupId);
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.userGroup WHERE ps.scheduleStartTime < :startTime AND ps.scheduleNotificationStart = :notified")
    List<PersonalScheduleJpaEntity> findByScheduleStartTimeBeforeAndScheduleNotificationStart(@Param("startTime") LocalDateTime startTime, @Param("notified") boolean notified);
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.userGroup WHERE ps.scheduleDoneTime > :endTime AND ps.scheduleNotificationDone = :notified")
    List<PersonalScheduleJpaEntity> findByScheduleDoneTimeAfterAndScheduleNotificationDone(@Param("endTime") LocalDateTime endTime, @Param("notified") boolean notified);
    PersonalScheduleJpaEntity findByPersonalScheduleId(Long personalScheduleId);

    void deleteByPersonalScheduleId(Long personalScheduleId);

    @Modifying
    @Query("UPDATE PersonalScheduleJpaEntity p " +
            "SET p.duration = :duration, " +
            "    p.scheduleStartTime = :startTime, " +
            "    p.scheduleDoneTime = :doneTime, " +
            "    p.scheduleStart = :start, " +
            "    p.scheduleDone = :done, " +
            "    p.scheduleNotificationStart = :notificationStart, " +
            "    p.scheduleNotificationDone = :notificationDone " +
            "WHERE p.personalScheduleId = :personalScheduleId")
    void updatePersonalSchedule(
            @Param("personalScheduleId") Long personalScheduleId,
            @Param("duration") Integer duration,
            @Param("startTime") LocalDateTime startTime,
            @Param("doneTime") LocalDateTime doneTime,
            @Param("start") Boolean start,
            @Param("done") Boolean done,
            @Param("notificationStart") Boolean notificationStart,
            @Param("notificationDone") Boolean notificationDone
    );
}

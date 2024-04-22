package com.example.personal;

import com.example.personal.PersonalScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalScheduleJpaRepository extends JpaRepository<PersonalScheduleJpaEntity, Long> {
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.groupSchedule Join FETCH ps.user WHERE ps.groupSchedule.scheduleId = :scheduleId")
    List<PersonalScheduleJpaEntity> findAllByScheduleScheduleId(Long scheduleId);
    PersonalScheduleJpaEntity findByPersonalScheduleId(Long personalScheduleId);
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.groupSchedule s JOIN FETCH ps.user u WHERE ps.scheduleStartTime < :scheduleStartTime AND ps.scheduleNotificationStart = :notificationSendStart")
    List<PersonalScheduleJpaEntity> findByScheduleStartTimeBeforeAndScheduleNotificationStart(
            @Param("scheduleStartTime") LocalDateTime scheduleStartTime,
            @Param("notificationSendStart") Boolean notificationSendStart
    );
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.groupSchedule s JOIN FETCH ps.user u WHERE ps.scheduleStartTime < :scheduleStartTime AND ps.scheduleNotificationDone = :notificationSendStart")
    List<PersonalScheduleJpaEntity> findByScheduleDoneTimeBeforeAndScheduleNotificationDone(
            @Param("scheduleStartTime") LocalDateTime scheduleStartTime,
            @Param("notificationSendStart") Boolean notificationSendStart
    );
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.personalScheduleRoutineList WHERE ps.groupSchedule.scheduleId = :scheduleId and ps.user.userId = :userId")
    PersonalScheduleJpaEntity findByScheduleIdWithScheduleRoutine(@Param("userId") Long userId, @Param("scheduleId") Long scheduleId);
    @Query("SELECT ps FROM PersonalScheduleJpaEntity ps JOIN FETCH ps.groupSchedule s WHERE ps.user.userId = :userId")
    List<PersonalScheduleJpaEntity> findAllByUserUserIdWithSchedule(@Param("userId") Long userId);
}

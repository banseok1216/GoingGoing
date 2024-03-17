package com.example.goinggoingdomain.domain.personal.repository;

import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalScheduleRepository extends JpaRepository<PersonalSchedule, Long> {
    @Query("SELECT ps FROM PersonalSchedule ps JOIN FETCH ps.groupSchedule Join FETCH ps.user WHERE ps.groupSchedule.scheduleId = :scheduleId")
    List<PersonalSchedule> findAllByScheduleScheduleId(Long scheduleId);
    PersonalSchedule findByPersonalScheduleId(Long personalScheduleId);
    @Query("SELECT ps FROM PersonalSchedule ps JOIN FETCH ps.groupSchedule s JOIN FETCH ps.user u WHERE ps.scheduleStartTime < :scheduleStartTime AND ps.scheduleNotificationStart = :notificationSendStart")
    List<PersonalSchedule> findByScheduleStartTimeBeforeAndScheduleNotificationStart(
            @Param("scheduleStartTime") LocalDateTime scheduleStartTime,
            @Param("notificationSendStart") Boolean notificationSendStart
    );
    @Query("SELECT ps FROM PersonalSchedule ps JOIN FETCH ps.groupSchedule s JOIN FETCH ps.user u WHERE ps.scheduleStartTime < :scheduleStartTime AND ps.scheduleNotificationDone = :notificationSendStart")
    List<PersonalSchedule> findByScheduleDoneTimeBeforeAndScheduleNotificationDone(
            @Param("scheduleStartTime") LocalDateTime scheduleStartTime,
            @Param("notificationSendStart") Boolean notificationSendStart
    );
    @Query("SELECT ps FROM PersonalSchedule ps JOIN FETCH ps.personalScheduleRoutineList WHERE ps.groupSchedule.scheduleId = :scheduleId and ps.user.userId = :userId")
    PersonalSchedule findByScheduleIdWithScheduleRoutine(@Param("userId") Long userId,@Param("scheduleId") Long scheduleId);
    @Query("SELECT ps FROM PersonalSchedule ps JOIN FETCH ps.groupSchedule s WHERE ps.user.userId = :userId")
    List<PersonalSchedule> findAllByUserUserIdWithSchedule(@Param("userId") Long userId);
}

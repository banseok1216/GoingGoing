package com.example.personal;

import com.example.personal.PersonalScheduleRoutineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalScheduleRoutineJpaRepository extends JpaRepository<PersonalScheduleRoutineJpaEntity, Long> {
    PersonalScheduleRoutineJpaEntity findByScheduleRoutineId(Long scheduleRoutineId);
    List<PersonalScheduleRoutineJpaEntity> findAllByPersonalScheduleJpaEntityPersonalScheduleId(Long personalScheduleId);
    void deleteByScheduleRoutineId(Long scheduleRoutineId);

    @Modifying
    @Query("UPDATE PersonalScheduleRoutineJpaEntity p " +
            "SET p.scheduleRoutineId = :routineId, " +
            "    p.scheduleRoutineIndex = :index " +
            "WHERE p.personalScheduleJpaEntity.personalScheduleId = :personalScheduleId")
    void updatePersonalScheduleOrder(
            @Param("personalScheduleId") Long personalScheduleId,
            @Param("routineId") Long routineId,
            @Param("index") Integer index
    );
}

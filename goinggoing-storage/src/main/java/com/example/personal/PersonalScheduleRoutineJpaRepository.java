package com.example.personal;

import com.example.personal.PersonalScheduleRoutineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalScheduleRoutineJpaRepository extends JpaRepository<PersonalScheduleRoutineJpaEntity, Long> {
    List<PersonalScheduleRoutineJpaEntity> findAllByPersonalScheduleId(Long personalSchedule_Id);

    PersonalScheduleRoutineJpaEntity findByScheduleRoutineId(Long scheduleRoutineId);
    void deleteByScheduleRoutineId(Long scheduleRoutineId);

}

package com.example.goinggoingdomain.domain.personal.repository;

import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalScheduleRoutineRepository extends JpaRepository<PersonalScheduleRoutine, Long> {
    List<PersonalScheduleRoutine> findAllByPersonalScheduleId(Long personalSchedule_Id);

    PersonalScheduleRoutine findByScheduleRoutineId(Long scheduleRoutineId);
    void deleteByScheduleRoutineId(Long scheduleRoutineId);

}

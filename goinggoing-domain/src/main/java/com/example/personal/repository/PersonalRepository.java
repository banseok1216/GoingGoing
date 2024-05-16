package com.example.personal.repository;

import com.example.personal.model.PersonalSchedule;
import com.example.routine.model.Routine;

public interface PersonalRepository {
    void modifyRoutineOrder(PersonalSchedule personalSchedule);
    void saveRoutine(PersonalSchedule personalSchedule);
    void modifyPersonalSchedule(PersonalSchedule personalSchedule);
    void removePersonalSchedule(PersonalSchedule personalSchedule);
    PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId);

    Routine readRoutine(Routine.RoutineId routineId);
    void removeRoutine(Routine routine);
}

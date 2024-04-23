package com.example.personal;

import com.example.routine.Routine;

public interface PersonalRepository {
    void savePersonalSchedule(PersonalSchedule personalSchedule);
    void removePersonalSchedule(PersonalSchedule personalSchedule);
    PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId);
    Routine readRoutine(Routine.RoutineId routineId);
    void removeRoutine(Routine routine);
}

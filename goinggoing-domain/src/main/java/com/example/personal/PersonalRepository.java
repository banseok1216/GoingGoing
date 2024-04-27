package com.example.personal;

import com.example.group.Group;
import com.example.group.GroupSchedule;
import com.example.routine.Routine;

import java.util.List;

public interface PersonalRepository {
    void modifyRoutineOrder(PersonalSchedule personalSchedule);
    void saveRoutine(PersonalSchedule personalSchedule);
    void modifyPersonalSchedule(PersonalSchedule personalSchedule);
    void removePersonalSchedule(PersonalSchedule personalSchedule);
    PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId);
    Routine readRoutine(Routine.RoutineId routineId);
    void removeRoutine(Routine routine);
}

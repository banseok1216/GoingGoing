package com.example.personal;

import com.example.group.GroupSchedule;
import com.example.routine.Routine;

import java.util.List;

public interface PersonalRepository {
    void savePersonalSchedule(PersonalSchedule personalSchedule);
    void removePersonalSchedule(PersonalSchedule personalSchedule);
    PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId);
    List<PersonalSchedule> readPersonalSchedules(GroupSchedule.GroupScheduleId groupScheduleId);
    Routine readRoutine(Routine.RoutineId routineId);
    void removeRoutine(Routine routine);
}

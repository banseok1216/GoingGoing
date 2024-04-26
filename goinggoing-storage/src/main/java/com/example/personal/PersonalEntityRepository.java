package com.example.personal;

import com.example.group.GroupSchedule;
import com.example.routine.Routine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonalEntityRepository implements PersonalRepository {

    @Override
    public void savePersonalSchedule(PersonalSchedule personalSchedule) {

    }

    @Override
    public void removePersonalSchedule(PersonalSchedule personalSchedule) {

    }

    @Override
    public PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId) {
        return null;
    }

    @Override
    public List<PersonalSchedule> readPersonalSchedules(GroupSchedule.GroupScheduleId groupScheduleId) {
        return null;
    }

    @Override
    public Routine readRoutine(Routine.RoutineId routineId) {
        return null;
    }

    @Override
    public void removeRoutine(Routine routine) {

    }
}

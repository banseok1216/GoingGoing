package com.example.personal;

import com.example.routine.Routine;
import org.springframework.stereotype.Repository;

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
    public Routine readRoutine(Routine.RoutineId routineId) {
        return null;
    }
}

package com.example.personal.mapper;


import com.example.personal.PersonalSchedule;
import com.example.routine.Routine;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {
    public PersonalSchedule.PersonalScheduleId mapToPersonalScheduleId(Long personalScheduleId) {
        return new PersonalSchedule.PersonalScheduleId(personalScheduleId);
    }
    public Routine.RoutineId mapToRoutineId(Long routineId) {
        return new Routine.RoutineId(routineId);
    }
}

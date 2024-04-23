package com.example.personal;

import com.example.routine.Routine;
import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalReader {
    private final PersonalRepository personalRepository;
    public PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId){
        return personalRepository.readPersonalSchedule(personalScheduleId);
    }
    public Routine readRoutine(Routine.RoutineId routineId){
        return personalRepository.readRoutine(routineId);
    }
}

package com.example.personal.implementation;

import com.example.personal.repository.PersonalRepository;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.model.Routine;
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

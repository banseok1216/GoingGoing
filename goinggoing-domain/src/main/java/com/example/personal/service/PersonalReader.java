package com.example.personal.service;

import com.example.personal.repository.PersonalRepository;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.domain.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

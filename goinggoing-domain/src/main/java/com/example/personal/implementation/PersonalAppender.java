package com.example.personal.implementation;

import com.example.personal.repository.PersonalRepository;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.model.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalAppender {
    private final PersonalRepository personalRepository;
    public void saveRoutine(PersonalSchedule personalSchedule){
        personalRepository.saveRoutine(personalSchedule);
    }
    public void appendRoutineInSchedule(PersonalSchedule personalSchedule,Routine routine){
        personalSchedule.getRoutineWindow().addRoutine(routine);
    }
}

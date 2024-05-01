package com.example.personal.service;

import com.example.personal.repository.PersonalRepository;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.domain.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalRemover {
    private final PersonalRepository personalRepository;
    public void removePersonalSchedule(PersonalSchedule personalSchedule){
        personalRepository.removePersonalSchedule(personalSchedule);
    }
    public void removeRoutine(Routine routine){
        personalRepository.removeRoutine(routine);
    }
}

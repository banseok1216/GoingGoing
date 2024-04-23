package com.example.personal;

import com.example.routine.Routine;
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

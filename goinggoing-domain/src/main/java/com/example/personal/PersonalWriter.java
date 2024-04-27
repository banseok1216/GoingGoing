package com.example.personal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalWriter {
    private final PersonalRepository personalRepository;
    public void modifyRoutineOrder(PersonalSchedule personalSchedule){
        personalRepository.modifyRoutineOrder(personalSchedule);
    }
    public void saveRoutine(PersonalSchedule personalSchedule){
        personalRepository.saveRoutine(personalSchedule);
    }
    public void modifyPersonalSchedule(PersonalSchedule personalSchedule){
        personalRepository.modifyPersonalSchedule(personalSchedule);
    }

}

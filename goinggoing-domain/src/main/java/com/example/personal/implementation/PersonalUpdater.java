package com.example.personal.implementation;

import com.example.personal.model.PersonalSchedule;
import com.example.personal.repository.PersonalRepository;
import com.example.routine.model.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalUpdater {
    private final PersonalRepository personalRepository;
    public void modifyRoutineOrder(PersonalSchedule personalSchedule){
        personalRepository.modifyRoutineOrder(personalSchedule);
    }
    public void updateRoutine(PersonalSchedule personalSchedule, Routine routine){
         personalSchedule.getRoutineWindow().modifyRoutine(routine);
    }
    public void modifyPersonalSchedule(PersonalSchedule personalSchedule){
        personalRepository.modifyPersonalSchedule(personalSchedule);
    }
    public void updateScheduleStatus(PersonalSchedule personalSchedule){
        personalSchedule.updateStatusAndTime();
    }
    public PersonalSchedule updatePersonalSchedule(PersonalSchedule source, PersonalSchedule target){
        return source.updatePersonalSchedule(target).updateStatusAndTime();
    }
}

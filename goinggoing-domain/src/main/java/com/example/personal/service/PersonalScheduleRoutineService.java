package com.example.personal.service;

import com.example.personal.implementation.PersonalReader;
import com.example.personal.implementation.PersonalRemover;
import com.example.personal.implementation.PersonalWriter;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.model.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalScheduleRoutineService {
    private final PersonalRemover personalRemover;
    private final PersonalWriter personalWriter;
    private final PersonalReader personalReader;
    @Transactional
    public void modifyScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine routine) {
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalSchedule.getScheduleRoutineWindow().modifyRoutine(routine);
        PersonalSchedule updatedPersonalSchedule = personalSchedule.updateStatusAndTime();
        personalWriter.modifyRoutineOrder(updatedPersonalSchedule);
    }
    public void createScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine routine) {
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalSchedule.getScheduleRoutineWindow().addRoutine(routine);
        personalWriter.saveRoutine(personalSchedule);
    }
    @Transactional
    public void deleteScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId,Routine.RoutineId routineId) {
        Routine routine = personalReader.readRoutine(routineId);
        personalRemover.removeRoutine(routine);
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        PersonalSchedule updatedPersonalSchedule = personalSchedule.updateStatusAndTime();
        personalWriter.modifyRoutineOrder(updatedPersonalSchedule);
    }
}

package com.example.personal.service;

import com.example.personal.*;
import com.example.routine.Routine;
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
    public void updateScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine routine) {
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalSchedule.getScheduleRoutineWindow().addRoutine(routine);
        PersonalSchedule updatedPersonalSchedule = personalSchedule.updateStatusAndTime();
        personalWriter.savePersonalSchedule(updatedPersonalSchedule);
    }

    public void createScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine routine) {
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalSchedule.getScheduleRoutineWindow().addRoutine(routine);
    }
    @Transactional
    public void deleteScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId,Routine.RoutineId routineId) {
        Routine routine = personalReader.readRoutine(routineId);
        personalRemover.removeRoutine(routine);
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        PersonalSchedule updatedPersonalSchedule = personalSchedule.updateStatusAndTime();
        personalWriter.savePersonalSchedule(updatedPersonalSchedule);
    }
}

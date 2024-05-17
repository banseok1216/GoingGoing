package com.example.personal.service;

import com.example.personal.implementation.PersonalAppender;
import com.example.personal.implementation.PersonalReader;
import com.example.personal.implementation.PersonalRemover;
import com.example.personal.implementation.PersonalUpdater;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.model.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalScheduleRoutineService {
    private final PersonalRemover personalRemover;
    private final PersonalAppender personalAppender;
    private final PersonalReader personalReader;
    private final PersonalUpdater personalUpdater;

    @Transactional
    public void modifyScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine routine) {
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalUpdater.updateRoutine(personalSchedule, routine);
        personalUpdater.modifyRoutineOrder(personalSchedule.updateStatusAndTime());
    }

    public void createScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine routine) {
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalAppender.appendRoutineInSchedule(personalSchedule, routine);
        personalAppender.saveRoutine(personalSchedule);
    }

    @Transactional
    public void deleteScheduleRoutine(PersonalSchedule.PersonalScheduleId personalScheduleId, Routine.RoutineId routineId) {
        Routine routine = personalReader.readRoutine(routineId);
        personalRemover.removeRoutine(routine);
        PersonalSchedule personalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalUpdater.updateRoutine(personalSchedule, routine);
        personalUpdater.updateScheduleStatus(personalSchedule);
        personalUpdater.modifyRoutineOrder(personalSchedule);
    }
}

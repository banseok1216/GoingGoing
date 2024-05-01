package com.example.personal.service;

import com.example.personal.model.PersonalSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PersonalScheduleService {
    private final PersonalReader personalReader;
    private final PersonalWriter personalWriter;

    public PersonalSchedule loadPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId) {
        return personalReader.readPersonalSchedule(personalScheduleId);
    }

    @Transactional
    public void modifyPersonalSchedule(PersonalSchedule personalSchedule) {
        PersonalSchedule savedPersonalSchedule = personalReader.readPersonalSchedule(personalSchedule.getId());
        PersonalSchedule newPersonalSchedule = PersonalSchedule.withId(
                savedPersonalSchedule.getId(),
                personalSchedule.getPersonalDuration(),
                savedPersonalSchedule.getPersonalScheduleTime(),
                savedPersonalSchedule.getPersonalScheduleStatus(),
                savedPersonalSchedule.getScheduleRoutineWindow(),
                savedPersonalSchedule.getUser(),
                savedPersonalSchedule.getPersonalScheduleSend()
        );
        personalWriter.modifyPersonalSchedule(newPersonalSchedule.updateStatusAndTime());
    }
}

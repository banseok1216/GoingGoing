package com.example.personal.service;

import com.example.personal.*;
import com.example.personal.PersonalSchedule.PersonalScheduleTime;

import com.example.personal.mapper.PersonalMapper;
import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PersonalScheduleService {
    private final PersonalReader personalReader;
    private final PersonalWriter personalWriter;
    private final PersonalRemover personalRemover;
    private final PersonalMapper personalMapper;

    public PersonalSchedule loadPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId) {
        return personalReader.readPersonalSchedule(personalScheduleId);
    }

    public void modifyPersonalSchedule(PersonalSchedule personalSchedule) {
        PersonalSchedule savedPersonalSchedule = personalReader.readPersonalSchedule(personalSchedule.getId());
        PersonalSchedule newPersonalSchedule = PersonalSchedule.withId(
                savedPersonalSchedule.getId(),
                personalSchedule.getPersonalDuration(),
                savedPersonalSchedule.getPersonalScheduleTime(),
                savedPersonalSchedule.getPersonalScheduleStatus(),
                savedPersonalSchedule.getScheduleRoutineWindow()
        );
        personalWriter.savePersonalSchedule(newPersonalSchedule.updateStatusAndTime());
    }

    public void addPersonalSchedule(PersonalSchedule personalSchedule) {
        personalWriter.savePersonalSchedule(personalSchedule.updateStatusAndTime());
    }

    public void removePersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId) {
        PersonalSchedule savedPersonalSchedule = personalReader.readPersonalSchedule(personalScheduleId);
        personalRemover.removePersonalSchedule(savedPersonalSchedule);
    }
}

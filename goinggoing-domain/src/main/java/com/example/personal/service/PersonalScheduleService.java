package com.example.personal.service;

import com.example.personal.implementation.PersonalAppender;
import com.example.personal.implementation.PersonalReader;
import com.example.personal.implementation.PersonalUpdater;
import com.example.personal.model.PersonalSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PersonalScheduleService {
    private final PersonalReader personalReader;
    private final PersonalUpdater personalUpdater;

    public PersonalSchedule loadPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId) {
        return personalReader.readPersonalSchedule(personalScheduleId);
    }

    @Transactional
    public void modifyPersonalSchedule(PersonalSchedule personalSchedule) {
        PersonalSchedule savedPersonalSchedule = personalReader.readPersonalSchedule(personalSchedule.getId());
        PersonalSchedule newPersonalSchedule = personalUpdater.updatePersonalSchedule(savedPersonalSchedule,personalSchedule);
        personalUpdater.modifyPersonalSchedule(newPersonalSchedule.updateStatusAndTime());
    }
}

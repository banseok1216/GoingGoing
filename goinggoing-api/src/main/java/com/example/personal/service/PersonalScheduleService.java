package com.example.personal.service;

import com.example.group.Group;
import com.example.group.GroupSchedule;
import com.example.personal.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

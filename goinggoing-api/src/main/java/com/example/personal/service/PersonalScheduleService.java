package com.example.personal.service;

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

    public List<PersonalSchedule> loadPersonalSchedules(GroupSchedule.GroupScheduleId groupScheduleId) {
        return personalReader.readPersonalSchedules(groupScheduleId);
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
        newPersonalSchedule.updateStatusAndTime();
        personalWriter.savePersonalSchedule(newPersonalSchedule.updateStatusAndTime());
    }
}
